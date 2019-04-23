package ch.hearc.spring.diconimaux.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.Animal;


@Controller
public class HomeController {
		private int nbAnimalPerPage = 1;

		@Autowired
		AnimalRepository animalRepository;
		@Autowired
		AlimentationRepository alimentationRepository;
		@Autowired
		ClassificationRepository classificationRepository;
		@Autowired
		LocationRepository locationRepository;
	
		
		@GetMapping("/")
		public String home(Map<String, Object> model) 
		{

			model.put("page", "Accueil");
			
			model.put("animals", animalRepository.findAll(new PageRequest(0,nbAnimalPerPage)));
			//model.put("animals", animalRepository.findAll(new PageRequest(0,1)));
			
			model.put("locations", locationRepository.findAll());

			model.put("classifications", classificationRepository.findAll());
			
			model.put("alimentations", alimentationRepository.findAll());

			
			return "home";
		}
		
		@GetMapping("/page/{page}")
		public String homePageable(Map<String, Object> model, @PathVariable int page)
		{

			model.put("page", "Accueil");
			
			
			
			model.put("animals", animalRepository.findAll(new PageRequest(page - 1,nbAnimalPerPage)));
			//model.put("animals", animalRepository.findAll(new PageRequest(0,1)));
			model.put("locations", locationRepository.findAll());

			model.put("classifications", classificationRepository.findAll());
			
			model.put("alimentations", alimentationRepository.findAll());

			
			return "home";
		}
		
		@GetMapping("/admin")
		public String admin(Map<String, Object> model) {
			model.put("page", "Admin");
			return "admin";
		}
		
		@GetMapping("/user")
		public String user(Map<String, Object> model) {
			model.put("page", "User");
			return "user";
		}
		
		@GetMapping("/animals")
		public String getAllAnimal(Map<String, Object> model)
		{
			model.put("animals", animalRepository.findAll());
			model.put("alimentations", alimentationRepository.findAll());
			
			//TODO HERE Query pour choper les alimentations de l'animal
			//model.put("alimentations", alimentationRepository.findByAnimalID())
			model.put("locations", locationRepository.findAll());
			model.put("classifications", classificationRepository.findAll());

			
			return "listeAnimal";
		}
		
		
		/*@GetMapping("/search")
		public String searchAnimal(Model model, BindingResult errors, @ModelAttribute("animal") Animal animal)
		{
			List<Animal> animals = this.animalRepository.findByName(animal.getName());
			//model.put("animals", animalRepository.findByName(animal.getName()));
			model.addAttribute("animals", animals);
			return "listeAnimal";
		}*/
}


