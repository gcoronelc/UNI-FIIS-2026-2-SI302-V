package pe.edu.uni.educaapp.prueba;

import java.util.List;
import java.util.Map;
import pe.edu.uni.educaapp.dto.PagoDto;
import pe.edu.uni.educaapp.service.ConsultaService;

/**
 *
 * @author PCB
 */
public class Prueba04 {
    
    
    public static void main(String[] args) {
        try {
            // Proceso
            ConsultaService consultaService = new ConsultaService();
            List<Map<String,?>> lista = consultaService.conCursos();
            // Salida
            for (Map<String,?> bean : lista) {
                System.out.println(bean);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
     
}
