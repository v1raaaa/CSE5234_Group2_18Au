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
import edu.osu.cse5234.model.ShippingInfo;
import edu.osu.cse5234.model.Item;

@Controller
@RequestMapping("/purchase")
public class Purchase {
	
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
		System.out.println(order.getItems().get(0).getName());
		request.setAttribute("order", order);
		return "OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
//		TODO: validate order
		System.out.println(order.getItems().get(0).getQuantity());
		System.out.println(order.getItems().get(0).getName());
		request.getSession().setAttribute("order", order); // save order in session for confirmation and price calculation
		return "redirect:/purchase/paymentEntry";
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryForm(HttpServletRequest request) {
		request.setAttribute("payment", new PaymentInfo());
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo, HttpServletRequest request) {
//		TODO: validate payment
		
		request.getSession().setAttribute("paymentInfo", paymentInfo);
		return "redirect:/purchase/shippingEntry";
	}
	
	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String shippingEntry(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("shippingInfo", new ShippingInfo());
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
	public String submitShipping(@ModelAttribute("shippingInfo") ShippingInfo shippingInfo, HttpServletRequest request) {
		request.setAttribute("shippingInfo", shippingInfo);
		request.getSession().setAttribute("shippingInfo", shippingInfo); // save shipping info for order confirmation
		return "redirect:/purchase/viewOrder";
	}
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request) {
		// view order attribute in request
		return "ViewOrder";
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder() {
		return "redirect:/purchase/viewConfirmation";
	}
	
	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmation() {
		return "Confirmation";
	}

}
