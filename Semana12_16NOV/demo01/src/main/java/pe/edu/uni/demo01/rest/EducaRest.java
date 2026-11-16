package pe.edu.uni.demo01.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.demo01.dto.CursoDto;
import pe.edu.uni.demo01.service.EducaService;

@RestController
@RequestMapping("/api/educa")
@CrossOrigin(origins = "*")
public class EducaRest {

	@Autowired
	private EducaService educaService;
	
	/*
	@PostMapping("/cursos")
	public CursoDto nuevoCurso( @RequestBody CursoDto bean ) {
		bean = educaService.nuevoCurso(bean);
		return bean;		
	}
	*/
	
	
	@PostMapping("/cursos")
	public ResponseEntity<?> nuevoCurso(@RequestBody CursoDto bean) {
		if(bean.getMatriculados() == null) {
			bean.setMatriculados(0);
		}
		try {
			bean = educaService.nuevoCurso(bean);
			// HTTP 201 Created: Estándar REST cuando se crea un nuevo recurso con éxito
			return ResponseEntity.status(HttpStatus.CREATED).body(bean);

		} catch (DataAccessException e) {
			// HTTP 500 Internal Server Error: Errores propios de SQL o conexión a base de datos
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error en la base de datos al registrar el curso."));

		} catch (RuntimeException e) {
			// HTTP 400 Bad Request: Errores de validación de negocio lanzados desde EducaService
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Map.of("error", e.getMessage()));

		} catch (Exception e) {
			// HTTP 500 Internal Server Error: Cualquier otro error inesperado
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Ocurrió un error inesperado en el servidor."));
		}
	}
	
}
