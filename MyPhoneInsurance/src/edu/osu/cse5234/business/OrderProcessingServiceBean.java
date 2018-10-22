package edu.osu.cse5234.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.osu.cse5234.business.model.Item;
import edu.osu.cse5234.model.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.util.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {
	
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
    }
    
    // TODO update
    public String processOrder(Order order) {	
    	if (validateItemAvailability(order)) {      		
    		entityManager.persist(order);
    		
    		ServiceLocator.getInventoryService().updateInventory(convertLineItemsToItemIDs(order.getLineItems()));
    		entityManager.flush();
    		return UUID.randomUUID().toString();
    	} throw new RuntimeException();
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
