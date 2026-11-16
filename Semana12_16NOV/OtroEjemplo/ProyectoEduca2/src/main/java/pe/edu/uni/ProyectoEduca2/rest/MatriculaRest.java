package pe.edu.uni.ProyectoEduca2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.ProyectoEduca2.dto.MatriculaDto;
import pe.edu.uni.ProyectoEduca2.service.MatriculaService;

@RestController
@RequestMapping("/api/v1/matriculas")
public class MatriculaRest {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<?> matricular(@RequestBody MatriculaDto bean){
        try {
            MatriculaDto dto = matriculaService.matricular(bean);
            return ResponseEntity.ok(dto);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("ERROR: " + e.getMessage());
        }
    }
}
