package pe.edu.uni.pedidoapp.service;

public class PedidoService {
    
    public double calcularImpuesto(double importe){
        // Validar
        validar(importe);
        // Variables
        double impuesto;
        // Proceso
        impuesto = importe * 0.18;
        // Reporte
        return impuesto;
    }
    
    public double calcularTotal(double importe){
        // Validar
        validar(importe);
        // Variables
        double total;
        // Proceso
        total = importe + calcularImpuesto(importe);
        // Reporte
        return total;
    }
    
    private void validar(double importe){
        if(importe<=0){
            String texto = "El importe debe ser mayor a cero.";
            throw new RuntimeException(texto);
        }
    }
       
}
