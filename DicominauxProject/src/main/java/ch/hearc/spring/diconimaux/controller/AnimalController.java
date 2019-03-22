package ch.hearc.spring.diconimaux.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.Animal;

@Controller

public class AnimalController 
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
	@GetMapping("/all")
	public String getAllAnimal(Map<String, Object> model)
	{
		model.put("animals", animalRepository.findAll());
		model.put("alimentations", alimentationRepository.findAll());
		model.put("locations", locationRepository.findAll());
		model.put("classifications", classificationRepository.findAll());
		
		return "listeAnimal";
	}
	
	@GetMapping("/formAnimal")
	public String animalForm(Model model) 
	{
		model.addAttribute("animal", new Animal());
		
		return "animal-form";
	}
		
	@PostMapping("/insertAnimal")
	public String insertAnimal(@ModelAttribute Animal animal, Model model) 
	{
			
		animalRepository.save(animal);
		
		return "listeAnimal";
		
	}

}
