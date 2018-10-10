package edu.osu.cse5234.business;

import edu.osu.cse5234.business.model.Item;
import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {

	
    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
    }

	@Override
	public Inventory getAvailableInventory() {
		Item screenInsurance = new Item("Screen Insurance", "5.00");
		Item batteryInsurance = new Item("Battery Insurance","2.00");
		Item cameraInsurance = new Item("Camera Insurance", "1.50");
		Item chargerInsurance = new Item("Charger Insurance", "2.50");
		Item bundledInsurance = new Item("Bundled Insurance", "9.00");
		
		ArrayList<Item> catalog = new ArrayList<Item>();
		catalog.add(screenInsurance);
		catalog.add(batteryInsurance);
		catalog.add(cameraInsurance);
		catalog.add(chargerInsurance);
		catalog.add(bundledInsurance);
		
		Inventory inventory = new Inventory();
		inventory.setItems(catalog);
		return inventory;
	}

	@Override
	public boolean validateQuantity(List<Item> items) {
		return true;
	}

	@Override
	public boolean updateInventory(List<Item> items) {
		return true;
	}

}
