package ch.hearc.spring.diconimaux.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
}

	/*
	 * public void addViewControllers(ViewControllerRegistry registry) {
	 * registry.addViewController("/").setViewName("home");
	 * registry.addViewController("/home").setViewName("home");
	 * registry.addViewController("/user").setViewName("user");
	 * registry.addViewController("/admin").setViewName("admin");
	 * registry.addViewController("/login").setViewName("login");
	 * registry.addViewController("/registration").setViewName("registration"); }
	 */

}
