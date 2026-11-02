package pe.edu.uni.demo01.service;

import org.springframework.stereotype.Service;

import pe.edu.uni.demo01.dto.MateDto;

@Service
public class MateService {

	public MateDto  sumar(MateDto bean) {
		bean.setOperacion("SUMA");
		bean.setRespuesta(bean.getN1() + bean.getN2());
		return bean;
	}
	
	public MateDto  producto(MateDto bean) {
		bean.setOperacion("PRODUCTO");
		bean.setRespuesta(bean.getN1() * bean.getN2());
		return bean;
	}
	
}
