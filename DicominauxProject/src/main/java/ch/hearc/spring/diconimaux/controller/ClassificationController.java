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

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.Classification;

@Controller

public class ClassificationController
{
	@Autowired
	AnimalRepository animalRepository;
	@Autowired
	AlimentationRepository alimentationRepository;
	@Autowired
	ClassificationRepository classificationRepository;
	@Autowired
	LocationRepository locationRepository;
	

	@GetMapping("/formClassification")
	public String formClassification(Model model)
	{
		model.addAttribute("classification", new Classification());				
		
		return "classification-add";
	}
	
	@PostMapping("/saveClassification")
	public String saveClassification(@Valid @ModelAttribute Classification classification, BindingResult errors,  Map<String, Object> model)
	{
		if(!errors.hasErrors())
		{
			classificationRepository.save(classification);
			
			model.put("animals", animalRepository.findAll(new PageRequest(0,PageableAnimal.nbAnimalPerPage)));
			
			model.put("locations", locationRepository.findAll());

			model.put("classifications", classificationRepository.findAll());
			
			model.put("alimentations", alimentationRepository.findAll());
		}
		
			
		return "home";
	}

	@DeleteMapping("/deleteClassification/{id}")
	public String deleteClassification(@ModelAttribute Classification classification, BindingResult errors, Map<String, Object> model) 
	{
		if(!errors.hasErrors())
		{
			classificationRepository.delete(classification);
			
			model.put("animals", animalRepository.findAll(new PageRequest(0,PageableAnimal.nbAnimalPerPage)));
			
			model.put("locations", locationRepository.findAll());

			model.put("classifications", classificationRepository.findAll());
			
			model.put("alimentations", alimentationRepository.findAll());
		}
		
		
		return "data-list";
	}

}