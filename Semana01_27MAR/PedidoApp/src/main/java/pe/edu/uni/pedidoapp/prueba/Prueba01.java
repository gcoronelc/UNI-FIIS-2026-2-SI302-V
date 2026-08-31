package pe.edu.uni.pedidoapp.prueba;

import pe.edu.uni.pedidoapp.service.PedidoService;

public class Prueba01 {
    
    public static void main(String[] args) {
        // Variables
        double importe, impuesto, total;
        
        // Datos
        importe = 500;
        
        // Proceso
        PedidoService service = new PedidoService();
        impuesto = service.calcularImpueso(importe);
        total = service.calcularTotal(importe);
        
        // Reporte
        System.out.println("Importe: " + importe);
        System.out.println("Impuesto: " + impuesto);
        System.out.println("Total: " + total);
        
    }
}
