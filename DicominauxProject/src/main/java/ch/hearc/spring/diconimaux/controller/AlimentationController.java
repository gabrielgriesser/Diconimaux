package ch.hearc.spring.diconimaux.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.Alimentation;
import ch.hearc.spring.diconimaux.model.User;
import ch.hearc.spring.diconimaux.service.UserService;

@Controller

public class AlimentationController 
{
	@Autowired
	AnimalRepository animalRepository;
	@Autowired
	AlimentationRepository alimentationRepository;
	@Autowired
	ClassificationRepository classificationRepository;
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/formAlimentation")
	public String formAlimentation(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if (user != null) {
			boolean isUserAdmin = auth.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			boolean isUserConnected = auth.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("USER"));
			model.addAttribute("userName", user.getUsername());
			model.addAttribute("isUserAdmin", isUserAdmin);
			model.addAttribute("isUserConnected", isUserConnected);
			model.addAttribute("userID", user.getId());
		}
		
		model.addAttribute("alimentation", new Alimentation());				
		
		return "alimentation-add";
	}
	
	@PostMapping("/saveAlimentation")
	public String saveAlimentation(@Valid @ModelAttribute Alimentation alimentation, BindingResult errors,  Map<String, Object> model)
	{
		if(!errors.hasErrors())
		{
			alimentationRepository.save(alimentation);
		}
		return "redirect:/";
	}

	@DeleteMapping("/deleteAlimentation/{id}")
	public String deleteAlimentation(@ModelAttribute Alimentation alimentation, BindingResult errors, Map<String, Object> model) 
	{
		if(!errors.hasErrors())
		{
			alimentationRepository.delete(alimentation);
		}
		return "redirect:/datas";
	}
}