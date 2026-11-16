package pe.edu.uni.demo01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@ToString
public class CursoDto {
	
	private Integer id;
	private String  nombre;
	private Integer vacantes;
	private Integer matriculados;
	private String  profesor;
	private Double  precio;

}
