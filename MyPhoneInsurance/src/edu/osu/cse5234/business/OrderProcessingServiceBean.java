package edu.osu.cse5234.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

import edu.osu.cse5234.business.model.Item;
import edu.osu.cse5234.model.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.util.ServiceLocator;

import com.chase.payment.CreditCardPayment;
import com.chase.payment.PaymentProcessorService;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	@WebServiceRef(wsdlLocation = 
		       "http://localhost:9080/ChaseBankApplication/PaymentProcessorService?wsdl")
	private PaymentProcessorService service;


	/**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
    }
    
    // TODO update
    public String processOrder(Order order) {
    	CreditCardPayment creditCardPayment = createCreditCardPayment(order.getPayment());
    	String paymentResponse = service.getPaymentProcessorPort().processPayment(creditCardPayment); 
    	int port = Integer.parseInt(paymentResponse);
    	if(port < 0 ) {
    		return "payment did not go through.";
    	}else {
    		//set confirmationNumber
    	}
    	
    	if (validateItemAvailability(order)) {      		
    		entityManager.persist(order);
    		
    		ServiceLocator.getInventoryService().updateInventory(convertLineItemsToItemIDs(order.getLineItems()));
    		entityManager.flush();
    		return UUID.randomUUID().toString();
    	} throw new RuntimeException();
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
}
