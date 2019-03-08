package ch.hearc.spring.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.hearc.spring.thymeleaf.data.AnimauxDAO;

@SpringBootApplication
public class ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
	}
	
	@Bean
	public AnimauxDAO etudiantsDAO () {
		return new AnimauxDAO();
	}
}
