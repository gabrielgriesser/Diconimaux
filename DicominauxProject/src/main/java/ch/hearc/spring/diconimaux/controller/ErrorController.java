package ch.hearc.spring.diconimaux.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorController 
{
	@RequestMapping("/")
	public void handleError() 
	{
		throw new RuntimeException("Exception");
	}
}
