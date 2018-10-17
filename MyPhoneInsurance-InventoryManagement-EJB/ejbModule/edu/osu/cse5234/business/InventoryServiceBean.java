package edu.osu.cse5234.business;

import edu.osu.cse5234.business.model.Item;
import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {

	
	@PersistenceContext
	private EntityManager entityManager;
	private final String MY_QUERY = "Select i from Item i";
	private List<Item> catalog = new ArrayList<Item>();

    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
    	catalog = null;
    }

	@Override
	public Inventory getAvailableInventory() {
		catalog = entityManager.createQuery(MY_QUERY, Item.class).getResultList();
		return new Inventory(catalog);
	}

	@Override
	public boolean validateQuantity(List<Item> items) {
		for(Item orderItem: items) {
			if(orderItem.getQuantity() > 0) {
				for(Item inventoryItem: catalog) {
					if(orderItem.getName().equals(inventoryItem.getName())) {
						if(orderItem.getQuantity() > inventoryItem.getQuantity()) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateInventory(List<Item> items) {
		return true;
	}

}
