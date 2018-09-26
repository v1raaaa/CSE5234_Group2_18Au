package edu.osu.cse5234.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.model.Item;

@Controller
@RequestMapping("/purchase")
public class Purchase {
	
//	TODO - add static list of Items to be added on init 
	private static List<Item> catalog; 
	
	@PostConstruct
	public void onInit() {		
		Item screenInsurance = new Item("Screen Insurance", "5.00");
		Item batteryInsurance = new Item("Battery Insurance","2.00");
		Item cameraInsurance = new Item("Camera Insurance", "1.50");
		Item chargerInsurance = new Item("Charger Insurance", "2.50");
		Item bundledInsurance = new Item("Bundled Insurance", "9.00");
		
		catalog = new ArrayList<Item>();
		catalog.add(screenInsurance);
		catalog.add(batteryInsurance);
		catalog.add(cameraInsurance);
		catalog.add(chargerInsurance);
		catalog.add(bundledInsurance);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request) {
		Order order = new Order();
		order.setItems(catalog);
		
		request.setAttribute("order", order);
		return "OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		request.getSession().setAttribute("order", order);
		return "redirect:/purchase/paymentEntry";
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryForm(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payment", new PaymentInfo());
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment() {
//		 TODO PRG on payment object
		return null;
	}
	
	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String shippingEntry() {
		return null;
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
//	TODO PRG on shipping object
	public String submitShipping() {
		return null;
	}
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrder() {
		return null;
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder() {
//		TODO assert this.order == order
		return null;
	}
	
	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmation() {
		return null;
	}

}
