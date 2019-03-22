package ch.hearc.spring.diconimaux.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;

@Controller

public class AnimalController 
{
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	@GetMapping("/all")
	public String getAll(Map<String, Object> model)
	{
		model.put("animals", animalRepository.findAll());
		
		return "liste";
	}
}
