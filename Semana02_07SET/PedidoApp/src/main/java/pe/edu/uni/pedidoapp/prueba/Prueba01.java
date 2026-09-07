package pe.edu.uni.pedidoapp.prueba;

import pe.edu.uni.pedidoapp.service.PedidoService;

public class Prueba01 {

    public static void main(String[] args) {
        // Datos
        double importe = 1000.0;
        // PRoceso
        PedidoService pedidoService = new PedidoService();
        double impuesto = pedidoService.calcularImpuesto(importe);
        double total = pedidoService.calcularTotal(importe);
        // Salida
        System.out.println("Importe: " + importe);
        System.out.println("Impuesto: " + impuesto);
        System.out.println("Total: " + total);
    }

    
}
