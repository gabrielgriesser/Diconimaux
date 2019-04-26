package ch.hearc.spring.diconimaux.controller;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.Alimentation;
import ch.hearc.spring.diconimaux.model.Animal;
import ch.hearc.spring.diconimaux.model.Classification;
import ch.hearc.spring.diconimaux.model.Location;

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
	
	
	@GetMapping("/formAnimal")
	public String animalForm(Model model)
	{
		model.addAttribute("animal", new Animal());
		
		List<Alimentation> alimentationList = alimentationRepository.findAll();
		model.addAttribute("alimentations", alimentationList);
		
		List<Classification> classificationList = classificationRepository.findAll();
		model.addAttribute("classifications", classificationList);
		
		List<Location> locationList = locationRepository.findAll();
		model.addAttribute("locations", locationList);
		
		return "animal-form";
	}
	
	@PostMapping("/saveAnimal")
	public String saveAnimal(@Valid @ModelAttribute Animal animal, BindingResult errors,  Map<String, Object> model)
	{
				
		animalRepository.save(animal);
		
		model.put("animals", animalRepository.findAll(new PageRequest(0,PageableAnimal.nbAnimalPerPage)));
		
		model.put("locations", locationRepository.findAll());

		model.put("classifications", classificationRepository.findAll());
		
		model.put("alimentations", alimentationRepository.findAll());
			
		return "home";
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@ModelAttribute Animal animal, Map<String, Object> model) 
	{
		animalRepository.delete(animal);
		
		model.put("animals", animalRepository.findAll());
		
		model.put("locations", locationRepository.findAll());

		model.put("classifications", classificationRepository.findAll());
		
		model.put("alimentations", alimentationRepository.findAll());
		
		return "home";
	}
	
	@GetMapping("/animal/{id}")
	public String animal(@ModelAttribute Animal animal, Model model) 
	{
		
		animalRepository.findById(animal.getId()).ifPresent(a -> model.addAttribute("animals", a));
				
		return "infoAnimal";
	}
	
	
	@GetMapping("/search")
	public String searchAnimalRequest(@RequestParam(value="animalName", required=false) String animalName, @RequestParam(value="locationName", required=false) String locationName, 
			@RequestParam(value="alimentationName", required=false) String alimentationName, @RequestParam(value="classificationName", required = false) String classificationName, Model model)
	{
	    
    	model.addAttribute("animals", animalRepository.findByQuery(animalName, locationName, classificationName, alimentationName));
	    
	    
	    return "infoAnimal";

	}
}
