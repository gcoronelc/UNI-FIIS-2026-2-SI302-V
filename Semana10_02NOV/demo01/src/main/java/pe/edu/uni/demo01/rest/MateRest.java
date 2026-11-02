package pe.edu.uni.demo01.rest;

import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.demo01.dto.MateDto;
import pe.edu.uni.demo01.service.MateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MateRest {
	
	@Autowired
	private MateService mateService;
	
	@PostMapping("/sumar")
	public MateDto sumar(@RequestBody MateDto bean ) {
		bean = mateService.sumar(bean);
		return bean;
	}
	
	@PostMapping("/producto")
	public MateDto producto(@RequestBody MateDto bean ) {
		bean = mateService.producto(bean);
		return bean;
	}
	
}
