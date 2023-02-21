package conversordivisas;

import util.Resultado;
import util.TipoCambioDivisas;

import javax.swing.*;
import java.util.Map;

public class ConversorDivisas {

    private static final String[] OPCIONES_CONVERSION = {"Dolar a peso mexicano", "Dolar a euro", "Dolar a libra",
            "Peso mexicano a dolar", "Euro a dolar", "Libra a dolar"};
    private final Map<String, Double> tipoCambioBaseDolar;

    public ConversorDivisas() {
        tipoCambioBaseDolar = TipoCambioDivisas.obtenerTipoCambioDivisas();
    }

    public void convertirDivisas(double cantidadConvertir) {
        String tipoConversion = obtenerTipoConversion();
        double resultadoConversion = definirTipoConversion(tipoConversion, cantidadConvertir);
        mostrarResultado(new Resultado(tipoConversion, resultadoConversion));
    }

    private String obtenerTipoConversion() {
        return (String) JOptionPane.showInputDialog(null, "Elige el tipo de conversion", "Monedas",
                JOptionPane.QUESTION_MESSAGE, null, OPCIONES_CONVERSION, OPCIONES_CONVERSION[0]);
    }

    private double definirTipoConversion(String tipoConversion, double cantidadConvertir) {
        switch (tipoConversion) {
            case "Dolar a peso mexicano":
                return realizarOperacionDesdeDolar(cantidadConvertir, tipoCambioBaseDolar.get("MXN"));
            case "Dolar a euro":
                return realizarOperacionDesdeDolar(cantidadConvertir, tipoCambioBaseDolar.get("EUR"));
            case "Dolar a libra":
                return realizarOperacionDesdeDolar(cantidadConvertir, tipoCambioBaseDolar.get("GBP"));
            case "Peso mexicano a dolar":
                return realizarOperacionHaciaDolar(cantidadConvertir, tipoCambioBaseDolar.get("MXN"));
            case "Euro a dolar":
                return realizarOperacionHaciaDolar(cantidadConvertir, tipoCambioBaseDolar.get("EUR"));
            case "Libra a dolar":
                return realizarOperacionHaciaDolar(cantidadConvertir, tipoCambioBaseDolar.get("GBP"));
            default:
                return 0;
        }
    }

    private double realizarOperacionDesdeDolar(double cantidadConvertir, double tipoCambio) {
        return cantidadConvertir * tipoCambio;
    }

    private double realizarOperacionHaciaDolar(double cantidadConvertir, double tipoCambio) {
        return cantidadConvertir / tipoCambio;
    }

    private void mostrarResultado(Resultado resultadoConversion) {
        JOptionPane.showMessageDialog(null, "Tienes: " + resultadoConversion.getResultado() + "\nConversion: " +
                resultadoConversion.getDescripcion(), "Conversion", JOptionPane.INFORMATION_MESSAGE);
    }
}