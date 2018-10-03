package edu.osu.cse5234.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import edu.osu.cse5234.model.Order;
//import edu.osu.cse5234.model.PaymentInfo;
//import edu.osu.cse5234.model.ShippingInfo;
//import edu.osu.cse5234.model.Item;

@Controller
@RequestMapping("/")
public class Home {
	@RequestMapping(method = RequestMethod.GET)
	public String viewHomePage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "Home";
	}
	
	@RequestMapping(path = "ContactUs", method = RequestMethod.GET)
	public String ContactUS(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "ContactUs";
	}
	
	@RequestMapping(path = "AboutUs", method = RequestMethod.GET)
	public String AboutUs(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "AboutUs";
	}
	
	

}
