package edu.osu.cse5234.model;

import edu.osu.cse5234.controller.*;

public class Item {
	private String name;
	private String price;
	private String quantity;

	public Item(String name, String price) {
		this.name = name;
		this.price = price;
	}
	
	public Item() {
		
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
