package com.cdac.smp.Location.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cdac.smp.Location.Model.Location;
import com.cdac.smp.Location.Service.LocationService;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    
    @GetMapping("/view")
    public String showLocations(Model model) {
        model.addAttribute("locations", locationService.viewAllLocations());
        return "locations"; 
    }

  
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("location", new Location());
        return "add_location";
    }

   
    @PostMapping("/add")
    public String addLocationForm(@ModelAttribute Location location) {
        locationService.addLocation(location);
        return "redirect:/locations/view";
    }

   
    @GetMapping("/edit/{loc_cd}")
    public String showEditForm(@PathVariable String loc_cd, Model model) {
        Location loc = locationService.viewAllLocations()
                       .stream()
                       .filter(l -> l.getLoc_cd().equals(loc_cd))
                       .findFirst()
                       .orElse(null);
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
