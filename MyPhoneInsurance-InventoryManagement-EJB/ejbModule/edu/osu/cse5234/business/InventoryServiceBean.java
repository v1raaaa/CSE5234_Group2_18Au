package edu.osu.cse5234.business;

import edu.osu.cse5234.business.model.Item;
import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;

import java.util.HashMap;
import java.util.List;

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

    public InventoryServiceBean() {
    }

	@Override
	public Inventory getAvailableInventory() {
		List<Item> catalog = entityManager.createQuery(MY_QUERY, Item.class).getResultList();
		
		return new Inventory(catalog);
	}

	@Override
	public boolean validateQuantity(List<Item> items) {
		Inventory inventory = getAvailableInventory();
		
		HashMap<String, Item> catalogMap = new HashMap<String, Item>();
		for (Item i : inventory.getItems()) {
			catalogMap.put(i.getName(), i);
		}
		
		for(Item orderItem : items) {
			if (catalogMap.containsKey(orderItem.getName())
				&& orderItem.getQuantity() > 0 
				&& orderItem.getQuantity() > catalogMap.get(orderItem.getName()).getQuantity()) {
					return false;
				}
		}
		return true;
	}

	@Override
	public boolean updateInventory(List<Item> items) {
		return true;
	}

}
