package pe.edu.uni.demo01.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Saludo {

	
	@GetMapping("/saludo")
	public String saludo() {
		return "Hola Gustavo";
	}
	
	
}
