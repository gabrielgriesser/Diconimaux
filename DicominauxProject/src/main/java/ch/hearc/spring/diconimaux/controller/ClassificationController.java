package ch.hearc.spring.diconimaux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.model.Classification;

@Controller

public class ClassificationController
{
	@Autowired
	ClassificationRepository classificationRepository;
	
	
	@GetMapping("/formClassification")
	public String classificationForm(Model model)
	{
		model.addAttribute("classification", new Classification());
		
		return "classification-form";
	}
		
	
	@PostMapping("/insertClassification")
	public String insertClassification(@ModelAttribute Classification classification, Model model)
	{
			
		classificationRepository.save(classification);
		
		return "listeAnimal";
		
	}

}