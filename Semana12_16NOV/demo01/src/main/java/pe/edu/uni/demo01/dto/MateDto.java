package pe.edu.uni.demo01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MateDto {
	
	private String operacion;
	private Integer n1;
	private Integer n2;
	private Integer respuesta;
		
}
