package edu.osu.cse5234.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CUSTOMER_ORDER_LINE_ITEM") 
public class LineItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7748037958689665693L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "ITEM_NUMBER")
	private int itemId;
	
	@Column(name = "ITEM_NAME")
	private String itemName;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	@Transient 
	private double price;

	public LineItem() {
		
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
