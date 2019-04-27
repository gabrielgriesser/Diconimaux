package ch.hearc.spring.diconimaux.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ch.hearc.spring.diconimaux.PageableAnimal;
import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;


@Controller
public class HomeController {

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
			
			model.put("animals", animalRepository.findAll(new PageRequest(0,PageableAnimal.nbAnimalPerPage)));
			
			model.put("locations", locationRepository.findAll());

			model.put("classifications", classificationRepository.findAll());
			
			model.put("alimentations", alimentationRepository.findAll());

			
			return "home";
		}
		
		@GetMapping("/page/{page}")
		public String homePageable(Map<String, Object> model, @PathVariable int page)
		{

			model.put("page", "Accueil");			
			
			model.put("animals", animalRepository.findAll(new PageRequest(page - 1,PageableAnimal.nbAnimalPerPage)));

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
		
		@GetMapping("/datas")
		public String getAllAnimal(Map<String, Object> model)
		{
			model.put("animals", animalRepository.findAll());
			model.put("alimentations", alimentationRepository.findAll());

			model.put("locations", locationRepository.findAll());
			model.put("classifications", classificationRepository.findAll());

			
			return "data-list";
		}
}


