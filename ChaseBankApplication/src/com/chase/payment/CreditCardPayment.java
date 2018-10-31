package com.chase.payment;

import java.io.Serializable;

public class CreditCardPayment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8760831185659381736L;
	
	private int id;
	private String cardHolderName;
	private String creditCardNumber;
	private String expirationDate;
	private String cvvCode;
	private double paymentAmount;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	
	public void getCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getCvvCode() {
		return cvvCode;
	}
	
	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	
	public double getPaymentAmount() {
		return paymentAmount;
	}
	
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	

}
