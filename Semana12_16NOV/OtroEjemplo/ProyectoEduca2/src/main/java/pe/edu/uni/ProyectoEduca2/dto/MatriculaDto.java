package pe.edu.uni.ProyectoEduca2.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDto {

    // Datos
    private int idCurso;
    private int idAlumno;
    private int idEmpleado;
    private int cuotas;
    private String tipo;

    // Resultado
    private Integer estado; // 1: Ok, -1: Error
    private String mensaje;

}
