package util;

public class Resultado {

    private String descripcion;
    private double resultado;

    public Resultado(String descripcion, double resultado) {
        this.descripcion = descripcion;
        this.resultado = resultado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getResultado() {
        return resultado;
    }
}
