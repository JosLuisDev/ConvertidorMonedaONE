package conversordivisas;

import util.Resultado;
import util.TipoCambioDivisas;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ConversorDivisas {

    private String [] opcionesConversion = {"Dolar a peso mexicano", "Dolar a euro","Dolar a libra",
            "Peso mexicano a dolar", "Euro a dolar","Libra a dolar"};
    private final Divisa divisa = new Divisa();
    private Map<String,Double> tipoCambioBaseDolar;

    public ConversorDivisas() {
        tipoCambioBaseDolar = TipoCambioDivisas.obtenerTipoCambioDivisas();
    }

    public void convertirDivisas(double cantidadConvertir){

        //Pedir el tipo de conversion ej peso a dolar, dolar a peso, etc
        String tipoConversion = (JOptionPane.showInputDialog(null,"Elige el tipo de conversion","Monedas", JOptionPane.QUESTION_MESSAGE,null,
                opcionesConversion,opcionesConversion[0]).toString());

        //Validar que operacion se realizara
        double resultadoConversion = definirTipoConversion(tipoConversion,cantidadConvertir);

        //Mostrar modal con resultado de la conversion
        mostrarResultado(new Resultado(divisa.getNombre(), resultadoConversion));
    }
    private double definirTipoConversion(String tipoConversion, double cantidadConvertir){
        double resultadoConversion;
        switch (tipoConversion){
//{"Dolar a peso mexicano", "Dolar a euro","Dolar a libra","Peso mexicano a dolar", "Euro a dolar","Libra a dolar"}
            case "Dolar a peso mexicano":
                setearDivisa("pesos mexicanos",tipoCambioBaseDolar.get("MXN"));
                resultadoConversion = realizarOperacionDesdeDolar(cantidadConvertir);
                break;
            case "Dolar a euro":
                setearDivisa("Euros", tipoCambioBaseDolar.get("EUR"));
                resultadoConversion = realizarOperacionDesdeDolar(cantidadConvertir);
                break;
            case "Dolar a libra":
                setearDivisa("Libras", tipoCambioBaseDolar.get("GBP"));
                resultadoConversion = realizarOperacionDesdeDolar(cantidadConvertir);
                break;
            case "Peso mexicano a dolar":
                setearDivisa("Dolares", tipoCambioBaseDolar.get("MXN"));
                resultadoConversion = realizarOperacionDesdeOtraDivisa(cantidadConvertir);
                break;
            case "Euro a dolar":
                setearDivisa("Dolares", tipoCambioBaseDolar.get("EUR"));
                resultadoConversion = realizarOperacionDesdeOtraDivisa(cantidadConvertir);
                break;
            case "Libra a dolar":
                setearDivisa("Dolares", tipoCambioBaseDolar.get("GBP"));
                resultadoConversion = realizarOperacionDesdeOtraDivisa(cantidadConvertir);
                break;
            default:
                resultadoConversion=0;
                break;
        }
        return resultadoConversion;
    }
    private void mostrarResultado(Resultado resultadoConversion){
        //Mostrar el resultado de la conversion
        JOptionPane.showMessageDialog(null,
                "Tienes: " + resultadoConversion.getResultado() + " " + resultadoConversion.getDescripcion(),
                "Conversion",JOptionPane.INFORMATION_MESSAGE);
    }
    private void setearDivisa(String nombre, double valor){
        divisa.setNombre(nombre);
        divisa.setValor(valor);
    }
    private double realizarOperacionDesdeDolar(double cantidadConvertir){
        return cantidadConvertir * divisa.getValor();
    }
    private double realizarOperacionDesdeOtraDivisa(double cantidadConvertir){
        return cantidadConvertir / divisa.getValor();
    }
}
