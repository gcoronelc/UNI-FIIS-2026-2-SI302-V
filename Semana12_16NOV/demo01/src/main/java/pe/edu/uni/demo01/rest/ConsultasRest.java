package pe.edu.uni.demo01.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.demo01.service.Consultas1Service;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*")
public class ConsultasRest {
	
	@Autowired
	private Consultas1Service consultas1Service;
	
	@GetMapping("/cursos/{idCurso}")
	public ResponseEntity<?> conPrecioCurso(@PathVariable int idCurso) {
	    try {
	        Double precio = consultas1Service.getPrecioCurso(idCurso);
	        
	        if (precio == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("El curso con ID " + idCurso + " no fue encontrado.");
	        }
	        
	        return ResponseEntity.ok(precio);
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Ocurrió un error al procesar la consulta: " + e.getMessage());
	    }
	}

}
