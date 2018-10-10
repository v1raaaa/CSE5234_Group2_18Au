package edu.osu.cse5234.business;

import java.util.UUID;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.util.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {

    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
    }
    
    // TODO update
    public String processOrder(Order order) {	
    	if (validateItemAvailability(order)) {
    		ServiceLocator.getInventoryService().updateInventory(order.getItems());
    		return UUID.randomUUID().toString();
    	} throw new RuntimeException();
    }
    
    public boolean validateItemAvailability(Order order) {
		return ServiceLocator.getInventoryService().validateQuantity(order.getItems());
    }
}
