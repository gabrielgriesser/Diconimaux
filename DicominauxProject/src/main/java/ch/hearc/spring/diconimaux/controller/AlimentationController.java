package ch.hearc.spring.diconimaux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.model.Alimentation;

@Controller

public class AlimentationController 
{
	@Autowired
	AlimentationRepository alimentationRepository;
	
	@GetMapping("/formAlimentation")
	public String alimentationForm(Model model)
	{
		// TODO
		// Récupérer les données passées par le formulaire pour les mettres dans le constructeur !!!
		model.addAttribute("alimentation", new Alimentation());
		
		return "alimentation-form";
	}
	

	
	@PostMapping("/insertAlimentation")
	public String insertAlimentation(@ModelAttribute Alimentation alimentation, Model model)
	{
			
		alimentationRepository.save(alimentation);
		
		return "alimentation-form";
		
	}
}