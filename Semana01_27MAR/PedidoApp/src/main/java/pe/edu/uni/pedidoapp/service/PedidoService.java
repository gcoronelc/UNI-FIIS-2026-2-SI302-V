package pe.edu.uni.pedidoapp.service;

public class PedidoService {
    
    
    private final double IGV = 0.18;
    
    
    public double calcularImpueso(double importe){
        double impuesto;
        impuesto = importe * IGV;
        return impuesto;
    }
    
    public double calcularTotal(double importe){
        double total;
        total = importe + calcularImpueso(importe);
        return total;
    }
    
}
