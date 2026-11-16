package pe.edu.uni.ProyectoEduca2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.ProyectoEduca2.service.ConsultasService;

@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin(origins = "*")
public class CursoRest {

    @Autowired
    private ConsultasService consultasService;


    // GET /api/v1/cursos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> findPrecioById(@PathVariable Long id) {
        try {
            double precio = consultasService.consPrecio(id);
            return ResponseEntity.ok(precio);
        }catch(EmptyResultDataAccessException e) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Codigo no encontrado");
        }
    }




}
