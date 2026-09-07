package pe.edu.uni.viaexitoapp.prueba;

import pe.edu.uni.viaexitoapp.dto.PagoDto;
import pe.edu.uni.viaexitoapp.service.PagoService;

public class Prueba01 {

    public static void main(String[] args) {
        // Datos
        int horasDia = 5;
        int dias = 20;
        double pagoHora = 150.00;
        PagoDto bean = new PagoDto(horasDia, dias, pagoHora);
        // Proceso
        PagoService service = new PagoService();
        service.procesar(bean);
        // Reporte
        System.out.println("Sueldo bruto: " + bean.getSueldoBruto());
        System.out.println("Impuesto: " + bean.getImpuesto());
        System.out.println("Sueldo neto: " + bean.getSueldoNeto());
        
        
    }

    
}
