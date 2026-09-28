package pe.edu.uni.educaapp.prueba;

import pe.edu.uni.educaapp.service.ConsultaService;

public class Prueba02 {

    public static void main(String[] args) {
        try {
            // Datos
            int idCurso = 555;
            // Proceso
            ConsultaService consultaService = new ConsultaService();
            double precio = consultaService.conPrecio(idCurso);
            // Salida
            System.out.println("Precio: " + precio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
