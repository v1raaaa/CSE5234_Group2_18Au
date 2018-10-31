package com.chase.payment;

public class PaymentProcessor {
	public String ping() {
		return "chase bank is online";
	}
	
	public String processPayment(CreditCardPayment creditPayment) {
		return "creditPayment";
	}

}
