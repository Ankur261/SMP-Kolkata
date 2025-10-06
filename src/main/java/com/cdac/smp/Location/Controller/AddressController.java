package com.cdac.smp.Location.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdac.smp.Location.Model.Address;
import com.cdac.smp.Location.Model.Location;
import com.cdac.smp.Location.Service.AddressService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	
	@GetMapping("/viewAddress")
	public String showAddresses(Model model){
		model.addAttribute("address",addressService.viewAllAddress());
		return "address";
		
	}
	

	@PostMapping("/add")
	public String addADdress(@ModelAttribute String loc_code,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "redirect:/address/add";
		}
		addressService.getLocationByCode(loc_code);
		return "redirecr:/address/viewAddress";
		
	}
	
	
}
