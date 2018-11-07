package edu.osu.cse5234.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceRef;

import edu.osu.cse5234.business.model.Item;
import edu.osu.cse5234.model.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.util.ServiceLocator;

import com.chase.payment.CreditCardPayment;
import com.chase.payment.PaymentProcessorService;
import com.ibm.json.java.JSONObject;
import com.ups.shipping.client.ShippingInitiationClient;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
@Resource(name="jms/emailQCF", lookup="jms/emailQCF", type=ConnectionFactory.class) 
public class OrderProcessingServiceBean {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	@WebServiceRef(wsdlLocation = 
		       "http://localhost:9080/ChaseBankApplication/PaymentProcessorService?wsdl")
	private PaymentProcessorService service;
	
	private static String shippingResourceURI = "http://localhost:9080/UPS/jaxrs";
	
	@Inject
	@JMSConnectionFactory("java:comp/env/jms/emailQCF")
	private JMSContext jmsContext;

	@Resource(lookup="jms/emailQ")
	private Queue queue;


	/**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
    }
    
    // TODO update
    public String processOrder(Order order) {
    	
    	if (validateItemAvailability(order)) {
        	CreditCardPayment creditCardPayment = createCreditCardPayment(order.getPayment());
        	String paymentResponse = service.getPaymentProcessorPort().processPayment(creditCardPayment); 
        	int confirmation = Integer.parseInt(paymentResponse);
        	
        	if (confirmation < 0) {
        		return ""; // payment did not go through
        	}
        	
    		ShippingInitiationClient shippingInitiationClient = new ShippingInitiationClient(shippingResourceURI);
    		    		
    		JsonObject response = shippingInitiationClient.invokeInitiateShipping(marshallShippingRequest(order));

    		if (response.containsKey("Accepted") && response.getBoolean("Accepted")) {
    			System.out.println(String.format("UPS Successfully accepted the order. OrderRefId: %d. ShippingReferenceNumber: %d",
    					response.getInt("OrderRefId"),
    					response.getInt("ShippingReferenceNumber")));   			
    			
    			order.getPayment().setConfirmationNumber(paymentResponse);
    			order.getShipping().setShippingRefNumber(response.getInt("ShippingReferenceNumber"));
            	entityManager.persist(order);
        		
        		ServiceLocator.getInventoryService().updateInventory(convertLineItemsToItemIDs(order.getLineItems()));
        		entityManager.flush();
        		
        		notifyUser(order.getEmailAddress());
        		
        		return paymentResponse;
    		}         	
    	} 
    	return "";
    }
    
    private JsonObject marshallShippingRequest(Order order) {
    	return Json.createObjectBuilder()
    			.add("Organization", "MyPhoneInsurance LLC.")
    			.add("OrderRefId", order.getId())
    			.add("Zip", order.getShipping().getZip())
    			.add("ItemsNumber", order.getLineItems().size())
    			.build();
    }
    
    private CreditCardPayment createCreditCardPayment(PaymentInfo payment) {
    	CreditCardPayment creditCardPayment = new CreditCardPayment();
    	
    	creditCardPayment.setHolderName(payment.getCardHolderName());
    	creditCardPayment.setCreditCardNumber(payment.getCreditCardNumber());
    	creditCardPayment.setExpirationDate(payment.getExpirationDate());
    	creditCardPayment.setCvvCode(payment.getCvvCode());
    	
    	
    	return creditCardPayment;
	}

	public boolean validateItemAvailability(Order order) {
		return ServiceLocator.getInventoryService().validateQuantity(convertLineItemsToItemIDs(order.getLineItems()));
    }
    
    private List<Item> convertLineItemsToItemIDs(List<LineItem> lineItems) {
    	List<Item> itemIds = new ArrayList<Item>(lineItems.size());
    	for (LineItem l : lineItems) {
    		itemIds.add(getItemID(l));
    	}
		return itemIds;
    }
    
    // TODO: update this
    private Item getItemID(LineItem lineItem) {
    	Item item = new Item();
    	item.setId(lineItem.getItemId());
    	return item;
    }
    
    public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	private void notifyUser(String customerEmail) {

		String message = customerEmail + ":" +
		       "Your order was successfully submitted. " + 
		     	"You will hear from us when items are shipped. " + 
		      	new Date();

		System.out.println("Sending message: " + message);
		jmsContext.createProducer().send(queue, message);
		System.out.println("Message Sent!");
	}

}
