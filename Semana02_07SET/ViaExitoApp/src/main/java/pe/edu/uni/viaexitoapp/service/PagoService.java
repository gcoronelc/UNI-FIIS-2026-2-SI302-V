package pe.edu.uni.viaexitoapp.service;

import pe.edu.uni.viaexitoapp.dto.PagoDto;

public class PagoService {


    public PagoDto procesar(PagoDto bean){
        // Variables
        int horasDia = bean.getHorasDia();
        int dias = bean.getDias();
        double pagoHora = bean.getPagoHora();
        // Proceso
        double sueldoBruto = horasDia * dias * pagoHora;
        double impuesto = (sueldoBruto>1500.00)?0.08:0;
        impuesto = impuesto * sueldoBruto;
        double sueldoNeto = sueldoBruto - impuesto;
        // Reporte
        bean.setSueldoBruto(sueldoBruto);
        bean.setImpuesto(impuesto);
        bean.setSueldoNeto(sueldoNeto);
        return bean;
    }
    
}
