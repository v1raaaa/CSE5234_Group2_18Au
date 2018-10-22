package edu.osu.cse5234.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.osu.cse5234.business.model.Item;
import edu.osu.cse5234.model.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.model.ShippingInfo;
import edu.osu.cse5234.util.ServiceLocator;

@Controller
@RequestMapping("/purchase")
public class Purchase {

	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request) {
		Order order = new Order();
		List<Item> items = ServiceLocator.getInventoryService().getAvailableInventory().getItems();
		List<LineItem> lineItems = new ArrayList<LineItem>(items.size());
		
		/* convert items to line items */
		for(Item item: items) {
			
			LineItem lineItem = new LineItem();
			
			lineItem.setItemId(item.getId());
			lineItem.setItemName(item.getName());
			lineItem.setPrice(item.getPrice());
			lineItem.setQuantity(0);
			lineItems.add(lineItem);
		}
		
		order.setLineItems(lineItems);
		
		request.setAttribute("order", order);
		return "OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		
		if (ServiceLocator.getOrderProcessingService().validateItemAvailability(order)) {
			request.getSession().setAttribute("order", order); // save order in session for confirmation and price calculation
			request.getSession().setAttribute("invalidItemAvailability", "");
			return "redirect:/purchase/paymentEntry";
		} 
	
		request.getSession().setAttribute("invalidItemAvailability", "Sorry, these item quantities are no longer available. Please resubmit.");
		System.out.println(request.getSession().getAttribute("invalidItemAvailability"));
		return "redirect:/purchase";
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryForm(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("order") == null) {
			return "redirect:/purchase";
		}
		
		
		request.setAttribute("paymentInfo", new PaymentInfo());
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo, HttpServletRequest request) {
		request.getSession().setAttribute("paymentInfo", paymentInfo);
		return "redirect:/purchase/shippingEntry";
	}
	
	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String shippingEntry(HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("paymentInfo") == null) {
			return "redirect:/purchase/paymentEntry";
		}
		
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
		
		if (request.getSession().getAttribute("shippingInfo") == null) {
			return "redirect:/purchase/shippingEntry";
		}
		
		Order currentOrder = (Order) request.getSession().getAttribute("order");
		List<LineItem> item_List = currentOrder.getLineItems();
		double total = 0;
		
		for(int i = 0; i < item_List.size(); i++) {
			LineItem y = item_List.get(i);
			int quantity = y.getQuantity() == 0 ? 0 : y.getQuantity();
			total += y.getPrice() * quantity;
		}
		
		request.setAttribute("totalPrice", total);
		request.getSession().setAttribute("totalPrice", total);
		return "ViewOrder";
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request) {
		Order order = (Order) request.getSession().getAttribute("order");
		ShippingInfo shippingInfo = (ShippingInfo) request.getSession().getAttribute("shippingInfo");
		PaymentInfo paymentInfo = (PaymentInfo) request.getSession().getAttribute("paymentInfo");
		order.setPayment(paymentInfo);
		order.setShipping(shippingInfo);
		order.setCustomerName(paymentInfo.getCardHolderName()); // TODO 
		order.setEmailAddress(shippingInfo.getEmail());
		String confirmationCode = ServiceLocator.getOrderProcessingService().processOrder((Order) request.getSession().getAttribute("order"));
		request.getSession().setAttribute("confirmationCode", confirmationCode);
		return "redirect:/purchase/viewConfirmation";
	}
	
	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmation(HttpServletRequest request) {
		return "Confirmation";
	}

}
