package pe.edu.uni.viaexitoapp.dto;

public class PagoDto {

    // Variables del input
    private int horasDia;
    private int dias;
    private double pagoHora;

    // Variables del output
    double sueldoBruto;
    double impuesto;
    double sueldoNeto;

    // Constructor por defecto
    public PagoDto() {
        this.horasDia = 0;
        this.dias = 0;
        this.pagoHora = 0.0;
        this.sueldoBruto = 0.0;
        this.impuesto = 0.0;
        this.sueldoNeto = 0.0;
    }

    // Constructor con parametros
    public PagoDto(int horasDia, int dias, double pagoHora) {
        this.horasDia = horasDia;
        this.dias = dias;
        this.pagoHora = pagoHora;
        this.sueldoBruto = 0.0;
        this.impuesto = 0.0;
        this.sueldoNeto = 0.0;
    }

    // Metodos getters and setters
    
    public int getHorasDia() {
        return horasDia;
    }

    public void setHorasDia(int horasDia) {
        this.horasDia = horasDia;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getPagoHora() {
        return pagoHora;
    }

    public void setPagoHora(double pagoHora) {
        this.pagoHora = pagoHora;
    }

    public double getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(double sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

}
