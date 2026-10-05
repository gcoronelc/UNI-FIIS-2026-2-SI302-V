package pe.edu.uni.educaapp.prueba;

import pe.edu.uni.educaapp.dto.MatriculaDto;
import pe.edu.uni.educaapp.service.ProcesosService;

public class Prueba10MatriculaYaExiste {

	public static void main(String[] args) {
		try {
			// Datos
			MatriculaDto bean = new MatriculaDto();
			bean.setIdCurso(1);
			bean.setIdAlumno(3);
			bean.setIdEmpleado(3);
			bean.setTipo("REGULAR");
			bean.setCuotas(3);
			// Proceso
			ProcesosService procesosService = new ProcesosService();
			bean = procesosService.matricular(bean);
			// Salida
			System.out.println("Estado: " + bean.getEstado());
			System.out.println("Mensaje: " + bean.getMensaje());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
