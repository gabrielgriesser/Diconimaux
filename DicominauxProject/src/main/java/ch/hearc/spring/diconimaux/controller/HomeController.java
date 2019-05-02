package ch.hearc.spring.diconimaux.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.User;
import ch.hearc.spring.diconimaux.service.UserService;

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

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		if (user != null) {
			model.addObject("userName", user.getUsername());
		}
		model.addObject("page", "Accueil");


		model.addObject("animals", animalRepository.findAll(new PageRequest(0, PageableAnimal.nbAnimalPerPage)));


		model.addObject("locations", locationRepository.findAll());

		model.addObject("classifications", classificationRepository.findAll());

		model.addObject("alimentations", alimentationRepository.findAll());
		model.setViewName("home");
		return model;
	}

	@GetMapping("/page/{page}")
	public String homePageable(Map<String, Object> model, @PathVariable int page) {

		model.put("page", "Accueil");

		model.put("animals", animalRepository.findAll(new PageRequest(page - 1, PageableAnimal.nbAnimalPerPage)));

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
	public String getAllAnimal(Map<String, Object> model) {
		model.put("animals", animalRepository.findAll());
		model.put("alimentations", alimentationRepository.findAll());

		model.put("locations", locationRepository.findAll());
		model.put("classifications", classificationRepository.findAll());

		return "data-list";
	}
}
