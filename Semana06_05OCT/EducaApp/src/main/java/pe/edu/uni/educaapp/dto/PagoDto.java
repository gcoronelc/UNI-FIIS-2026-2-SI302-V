package pe.edu.uni.educaapp.dto;

public class PagoDto {

    private int idAlumno;
    private int cuota;
    private double importe;
    private String fecha;

    public PagoDto() {
    }

    public PagoDto(int idAlumno, int cuota, double importe, String fecha) {
        this.idAlumno = idAlumno;
        this.cuota = cuota;
        this.importe = importe;
        this.fecha = fecha;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        String texto = "[Alumno:" + idAlumno;
        texto += ", Cuota:" + cuota;
        texto += ", Fecha:" + fecha;
        texto += ", Importe:" + importe + "]";
        return texto;
    }

    
    
}
