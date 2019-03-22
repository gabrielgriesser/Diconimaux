package ch.hearc.spring.diconimaux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.Location;

@Controller

public class LocationController 
{
	@Autowired
	LocationRepository locationRepository;
	

	@GetMapping("/formLocation")
	public String locationForm(Model model)
	{
		model.addAttribute("location", new Location());
		
		return "location-form";
	}
		
	@PostMapping("/insertLocation")
	public String insertLocation(@ModelAttribute Location location, Model model) {
			
		locationRepository.save(location);
		
		return "location-form";
		
	}

}