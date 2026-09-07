package pe.edu.uni.pedidoapp.prueba;

import pe.edu.uni.pedidoapp.service.PedidoService;

public class Prueba02 {

    public static void main(String[] args) {
        try {
            // Datos
            double importe = -1000.0;
            // PRoceso
            PedidoService pedidoService = new PedidoService();
            double impuesto = pedidoService.calcularImpuesto(importe);
            double total = pedidoService.calcularTotal(importe);
            // Salida
            System.out.println("Importe: " + importe);
            System.out.println("Impuesto: " + impuesto);
            System.out.println("Total: " + total);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    
}
