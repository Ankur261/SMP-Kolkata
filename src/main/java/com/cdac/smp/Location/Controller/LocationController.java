package com.cdac.smp.Location.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.smp.Location.Model.Documents;
import com.cdac.smp.Location.Model.Location;
import com.cdac.smp.Location.Service.DocumentService;
import com.cdac.smp.Location.Service.LocationService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private DocumentService documentService;

    
    @GetMapping("/view")
    public String showLocations(Model model) {
        model.addAttribute("locations", locationService.viewAllLocations());
        model.addAttribute("documents", documentService.getAllDocuments());
        return "locations"; 
    }

  
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("location", new Location());
        model.addAttribute("document", new Documents());
        return "add_location";
    }

   
    @PostMapping("/add")
    public String addLocationForm( @Valid @ModelAttribute Location location,BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()) {
    		 return "redirect:/locations/add";
    	}
        locationService.addLocation(location);
        return "redirect:/locations/view";
    }
    @GetMapping("/edit/{loc_cd}")
    public String showEditForm(@PathVariable String loc_cd, Model model) {
   
        Location loc = locationService.getLocationByCode(loc_cd); 
        model.addAttribute("location", loc);
        return "edit_location";
    }

   
    @PostMapping("/update")
    public String updateLocationForm(@ModelAttribute Location location) {
        locationService.updateLocation(location);
        return "redirect:/locations/view";
    }
    
    @PostMapping("/delete/{loc_cd}")
    public String deleteLocation(@PathVariable String loc_cd) {
        locationService.deleteLocation(loc_cd);
        return "redirect:/locations/view";
    }

  



    
}
