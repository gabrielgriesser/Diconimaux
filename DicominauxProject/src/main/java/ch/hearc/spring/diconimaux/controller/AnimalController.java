package ch.hearc.spring.diconimaux.controller;
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

import ch.hearc.spring.diconimaux.PageableAnimal;
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
	
	private Long animalID;
	private String animalName;
	private String animalDescription;
	private Location animalLocation;
	private Classification animalClassification;
	private Alimentation animalAlimentation;
	private int animalHeight;
	private int animalWeight;
	
	@GetMapping("/animal/{id}")
	public String infoAnimal(@ModelAttribute Animal animal, Model model) 
	{
		
		animalRepository.findById(animal.getId()).ifPresent(a -> model.addAttribute("animals", a));
				
		return "animal-info";
	}
	
	@GetMapping("/formAnimal")
	public String formAnimal(Model model)
	{
		model.addAttribute("animal", new Animal());
		
		model.addAttribute("locations", locationRepository.findAll());
		
		model.addAttribute("classifications", classificationRepository.findAll());

		model.addAttribute("alimentations", alimentationRepository.findAll());
		
		
		return "animal-add";
	}
	
	@GetMapping("/search")
	public String searchAnimal(
			@RequestParam(value="animalName", required=false) String animalName, 
			@RequestParam(value="locationName", required=false) String locationName, 
			@RequestParam(value="alimentationName", required=false) String alimentationName, 
			@RequestParam(value="classificationName", required = false) String classificationName, 
			Model model)
	{
	    
    	model.addAttribute("animals", animalRepository.findByQuery(animalName, locationName, classificationName, alimentationName));
	    
	    
	    return "animal-info";

	}
	
	@PostMapping("/saveAnimal")
	public String saveAnimal(@Valid @ModelAttribute Animal animal, BindingResult errors,  Map<String, Object> model)
	{
		if(!errors.hasErrors())
		{
			animalRepository.save(animal);
			
			model.put("animals", animalRepository.findAll(new PageRequest(0,PageableAnimal.nbAnimalPerPage)));
			
			model.put("locations", locationRepository.findAll());

			model.put("classifications", classificationRepository.findAll());
			
			model.put("alimentations", alimentationRepository.findAll());
		}
		
			
		return "home";
	}
	
	@PostMapping("/updateForm")
	public String formUpdateAnimal(
			@RequestParam(value="animalID", required=false) String strID, 
			@RequestParam(value="animalName", required=false) String strName, 
			@RequestParam(value="animalDescription", required=false) String strDescription, 
			@RequestParam(value="animalLocation", required=false) String strLocation, 
			@RequestParam(value="animalAlimentation", required=false) String strAlimentation, 
			@RequestParam(value="animalClassification", required=false) String strClassification, 
			@RequestParam(value="animalHeight", required=false) String strHeight, 
			@RequestParam(value="animalWeight", required=false) String strWeight, 
			@ModelAttribute Animal animal, 
			BindingResult errors, 
			Model model)
	{
		

		saveAnimalValues(strID, strName, strDescription, strLocation, strAlimentation, strClassification, strHeight, strWeight);

		animalRepository.findById(animalID).ifPresent(a -> model.addAttribute("animals", a));
		
		model.addAttribute("locations", locationRepository.findAll());

		model.addAttribute("classifications", classificationRepository.findAll());
		
		model.addAttribute("alimentations", alimentationRepository.findAll());
		
		return "animal-update";
	}
	
	@PostMapping("/update")
	public String updateAnimal(@ModelAttribute Animal animal, BindingResult errors,  Model model)
	{
		if(!errors.hasErrors())
		{
			Animal a = animalRepository.getOne(animalID);
			
			a.setId(animalID);
			
			System.out.println("NAME : " + animal.getName());
			System.out.println("DESCRIPTION : " + animal.getDescription());
			System.out.println("Height : " + animal.getHeight());
			
			if(animal.getName() == "")
				a.setName(animalName);
			else
				a.setName(animal.getName());
			
			if(animal.getDescription() == "")
				a.setDescription(animalDescription);
			else
				a.setDescription(animal.getDescription());
			
			if(animal.getAlimentationObject() == null)
				a.setAlimentation(animalAlimentation);
			else
				a.setAlimentation(animal.getAlimentationObject());
			
			if(animal.getClassificationObject() == null)
				a.setClassification(animalClassification);
			else
				a.setClassification(animal.getClassificationObject());
			
			if(animal.getLocationObject() == null)
				a.setLocation(animalLocation);
			else
				a.setLocation(animal.getLocationObject());
			
			if(animal.getHeight() == null)
				a.setHeight(animalHeight);
			else
				a.setHeight(animal.getHeight());
			
			if(animal.getWeight() == null)
				a.setWeight(animalWeight);
			else
				a.setWeight(animal.getWeight());
			
			animalRepository.save(a);
			
			model.addAttribute("animals", animalRepository.findAll(new PageRequest(0,PageableAnimal.nbAnimalPerPage)));
			
			model.addAttribute("locations", locationRepository.findAll());

			model.addAttribute("classifications", classificationRepository.findAll());
			
			model.addAttribute("alimentations", alimentationRepository.findAll());
			
		}
		
		return "home";
	}
		
	@DeleteMapping("/delete/{id}")
	public String deleteAnimal(@ModelAttribute Animal animal, BindingResult errors, Map<String, Object> model) 
	{
		if(!errors.hasErrors())
		{
			animalRepository.delete(animal);
			
			model.put("animals", animalRepository.findAll(new PageRequest(0,PageableAnimal.nbAnimalPerPage)));
			
			model.put("locations", locationRepository.findAll());

			model.put("classifications", classificationRepository.findAll());
			
			model.put("alimentations", alimentationRepository.findAll());
		}
		
		
		return "home";
	}
	
	public void saveAnimalValues(String strID, String strName, String strDescription, String strLocation, String strAlimentation, String strClassification, String strHeight, String strWeight)
	{
		animalID = Long.parseLong(strID);
		animalName = strName;
		animalDescription = strDescription;
		animalLocation = locationRepository.findByName(strLocation);
		animalClassification = classificationRepository.findByName(strClassification);
		animalAlimentation = alimentationRepository.findByName(strAlimentation);
		animalHeight = Integer.parseInt(strHeight);
		animalWeight = Integer.parseInt(strWeight);
	}
}
