package net.bozorgi.controller;

import java.util.List;

import javax.validation.Valid;

import net.bozorgi.dao.Offers;
import net.bozorgi.service.OffersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OffersController {

	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping("/offers")
	public String showOffers(Model model) {

		List<Offers> offers = offersService.getCurrent();
		model.addAttribute("offers", offers);
		return "offers";

	}

	@RequestMapping("/createoffer")
	public String createOffers(Model model) {
		
		model.addAttribute("offers", new Offers());
		
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid Offers offer, BindingResult result) {
		
		
		if(result.hasErrors()) {
			return "createoffer";
		}
		
		System.out.println("doCreate method in OffersController");
		offersService.create(offer);
		
		return "offercreated";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String myId) {
		
		System.out.println("Id is: " + myId);
		return "test";

	}
	
}
