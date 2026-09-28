package pe.edu.uni.educaapp.prueba;

import java.util.List;
import pe.edu.uni.educaapp.dto.PagoDto;
import pe.edu.uni.educaapp.service.ConsultaService;

/**
 *
 * @author PCB
 */
public class Prueba03 {
    
    
    public static void main(String[] args) {
        try {
            // Datos
            int idCurso = 2;
            // Proceso
            ConsultaService consultaService = new ConsultaService();
            List<PagoDto> lista = consultaService.conPagos(idCurso);
            // Salida
            for (PagoDto bean : lista) {
                System.out.println(bean);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
     
}
