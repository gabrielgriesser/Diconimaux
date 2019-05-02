package ch.hearc.spring.diconimaux.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.Alimentation;
import ch.hearc.spring.diconimaux.model.Animal;
import ch.hearc.spring.diconimaux.model.Classification;
import ch.hearc.spring.diconimaux.model.Location;
import ch.hearc.spring.diconimaux.model.User;
import ch.hearc.spring.diconimaux.service.UserService;

@Controller

public class AnimalController {
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

	private Long animalID;
	private String animalName;
	private String animalDescription;
	private Location animalLocation;
	private Classification animalClassification;
	private Alimentation animalAlimentation;
	private int animalHeight;
	private int animalWeight;

	@GetMapping("/animal/{id}")
	public ModelAndView infoAnimal(@ModelAttribute Animal animal) {
		ModelAndView model = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if (user != null) {
			boolean isUserAdmin = auth.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ADMIN"));
			boolean isUserConnected = auth.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("USER"));
			model.addObject("userName", user.getUsername());
			model.addObject("isUserAdmin", isUserAdmin);
			model.addObject("isUserConnected", isUserConnected);
			model.addObject("userID", user.getId());
		}

		animalRepository.findById(animal.getId()).ifPresent(a -> model.addObject("animals", a));
		model.setViewName("animal-info");

		return model;
	}

	@GetMapping("/formAnimal")
	public String formAnimal(Model model) {
		
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
		
		model.addAttribute("animal", new Animal());

		model.addAttribute("locations", locationRepository.findAll());

		model.addAttribute("classifications", classificationRepository.findAll());

		model.addAttribute("alimentations", alimentationRepository.findAll());

		return "animal-add";
	}

	@GetMapping("/search")
	public String searchAnimal(@RequestParam(value = "animalName", required = false) String animalName,
			@RequestParam(value = "locationName", required = false) String locationName,
			@RequestParam(value = "alimentationName", required = false) String alimentationName,
			@RequestParam(value = "classificationName", required = false) String classificationName, Model model) {

		model.addAttribute("animals",
				animalRepository.findByQuery(animalName, locationName, classificationName, alimentationName));

		return "animal-info";

	}

	@RequestMapping(value = "/saveAnimal", method = RequestMethod.POST)
	public ModelAndView saveAnimal(@RequestParam("file") MultipartFile file, @Valid @ModelAttribute Animal animal,
			BindingResult errors, RedirectAttributes redirectAttributes) 
	{
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		if (user != null) {
			animal.setUserId(user.getId());
		}

		if (!errors.hasErrors()) {
			try {
				// Get the file and save it somewhere
				byte[] bytes = file.getBytes();
				Path path = Paths.get("src/main/resources/static/images/" + file.getOriginalFilename());
				Files.write(path, bytes);
				animal.setImage_url(file.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
			animalRepository.save(animal);
		}		
		return new ModelAndView("redirect:/");
	}

	@PostMapping("/updateForm")
	public String formUpdateAnimal(@RequestParam(value = "animalID", required = false) String strID,
			@RequestParam(value = "animalName", required = false) String strName,
			@RequestParam(value = "animalDescription", required = false) String strDescription,
			@RequestParam(value = "animalLocation", required = false) String strLocation,
			@RequestParam(value = "animalAlimentation", required = false) String strAlimentation,
			@RequestParam(value = "animalClassification", required = false) String strClassification,
			@RequestParam(value = "animalHeight", required = false) String strHeight,
			@RequestParam(value = "animalWeight", required = false) String strWeight, @ModelAttribute Animal animal,
			BindingResult errors, Model model) {

		saveAnimalValues(strID, strName, strDescription, strLocation, strAlimentation, strClassification, strHeight,
				strWeight);

		animalRepository.findById(animalID).ifPresent(a -> model.addAttribute("animals", a));
		
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

		model.addAttribute("locations", locationRepository.findAll());

		model.addAttribute("classifications", classificationRepository.findAll());

		model.addAttribute("alimentations", alimentationRepository.findAll());

		return "animal-update";
	}

	@PostMapping("/update")
	public String updateAnimal(@RequestParam("file") MultipartFile file, @ModelAttribute Animal animal,
			BindingResult errors, Model model) {
		if (!errors.hasErrors()) {
			Animal a = animalRepository.getOne(animalID);

			a.setId(animalID);

			System.out.println("NAME : " + animal.getName());
			System.out.println("DESCRIPTION : " + animal.getDescription());
			System.out.println("Height : " + animal.getHeight());

			if (animal.getName().isBlank())
				a.setName(animalName);
			else
				a.setName(animal.getName());

			if (animal.getDescription().isBlank())
				a.setDescription(animalDescription);
			else
				a.setDescription(animal.getDescription());

			if (animal.getAlimentationObject() == null)
				a.setAlimentation(animalAlimentation);
			else
				a.setAlimentation(animal.getAlimentationObject());

			if (animal.getClassificationObject() == null)
				a.setClassification(animalClassification);
			else
				a.setClassification(animal.getClassificationObject());

			if (animal.getLocationObject() == null)
				a.setLocation(animalLocation);
			else
				a.setLocation(animal.getLocationObject());

			if (animal.getHeight() == null)
				a.setHeight(animalHeight);
			else
				a.setHeight(animal.getHeight());

			if (animal.getWeight() == null)
				a.setWeight(animalWeight);
			else
				a.setWeight(animal.getWeight());

			try {

				// Get the file and save it somewhere
				byte[] bytes = file.getBytes();
				Path path = Paths.get("src/main/resources/static/images/" + file.getOriginalFilename());
				Files.write(path, bytes);
				a.setImage_url(file.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}

			animalRepository.save(a);
		}

		return "redirect:/";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteAnimal(@ModelAttribute Animal animal, BindingResult errors, Map<String, Object> model) {
		if (!errors.hasErrors()) {
			animalRepository.delete(animal);
		}
		return "redirect:/";
	}

	public void saveAnimalValues(String strID, String strName, String strDescription, String strLocation,
			String strAlimentation, String strClassification, String strHeight, String strWeight) {
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
