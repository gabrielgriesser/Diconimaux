package ch.hearc.spring.diconimaux.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.data.AnimalsDAO;
import ch.hearc.spring.diconimaux.data.ClassificationsDAO;
import ch.hearc.spring.diconimaux.data.FeedsDAO;
import ch.hearc.spring.diconimaux.data.LocationsDAO;
import ch.hearc.spring.diconimaux.model.Animal;

@Controller
public class AccueilController {

		@Autowired
		AnimalsDAO animalsDao;
		ClassificationsDAO classificationsDao;
		FeedsDAO feedsDao;
		LocationsDAO locationsDao;
		
		@Value("${accueil.message:test}")
		private String message;
		
		@Value("${accueil1.message:[default]}")
		private String messageParDefaut;

		@GetMapping("/")
		public String accueil(Map<String, Object> model) {
			model.put("page", "Accueil");
			return "accueil";
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
		public String basic(Map<String, Object> model) {
			model.put("message", this.message);
			model.put("page", "Concepts de bases");
			
			model.put("message", this.message);
			model.put("messageParDefaut", this.messageParDefaut);
			model.put("page", "Concepts de bases");
			
			
			model.put("animals", animalsDao.getAllAnimals());
			return "animals";
		}
		
		@GetMapping("/form")
		public String form(Map<String, Object> model) {
			model.put("animal", new Animal());
			model.put("page", "Formulaire");
			return "formulaire";
		}
		
		@PostMapping("/animal")
		public String saveAnimal(@Valid @ModelAttribute Animal animal, BindingResult errors, Model model) {
					
			if (!errors.hasErrors()) {
	            animalsDao.save(animal);
	            
	            
	        }
	        return ((errors.hasErrors()) ? "animal" : "redirect:basic");
		}
}


