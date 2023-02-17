package conversordivisas;

import util.Resultado;

import javax.swing.*;

public class ConversorDivisas {

    private String [] opcionesConversion = {"Peso mexicano a dolar", "Peso mexicano a euro","Peso mexicano a libra",
            "Dolar a peso mexicano", "Euro a peso mexicano","Libra a peso mexicano"};
    private final Divisa divisa = new Divisa();

    public void convertirDivisas(double cantidadConvertir){

        //Pedir el tipo de conversion ej peso a dolar, dolar a peso, etc
        String tipoConversion = (JOptionPane.showInputDialog(null,"Elige el tipo de conversion","Monedas", JOptionPane.QUESTION_MESSAGE,null,
                opcionesConversion,opcionesConversion[0]).toString());

        //Validar que operacion se realizara
        double resultadoConversion = definirTipoConversion(tipoConversion,cantidadConvertir);

        //Mostrar modal con resultado de la conversion
        mostrarResultado(new Resultado(divisa.getNombre(), resultadoConversion));
    }

    private double realizarOperacion(double cantidadConvertir){
        return cantidadConvertir * divisa.getValor();
    }

    private void mostrarResultado(Resultado resultadoConversion){
        //Mostrar el resultado de la conversion
        JOptionPane.showMessageDialog(null,
                "Tienes: " + resultadoConversion.getResultado() + " " + resultadoConversion.getDescripcion(),
                "Conversion",JOptionPane.INFORMATION_MESSAGE);
    }

    private double definirTipoConversion(String tipoConversion, double cantidadConvertir){
        double resultadoConversion;
        switch (tipoConversion){
            case "Peso mexicano a dolar":
                setearDivisa("Dolares",0.054);
                resultadoConversion = realizarOperacion(cantidadConvertir);
                break;
            case "Peso mexicano a euro":
                setearDivisa("Euros", 0.051);
                resultadoConversion = realizarOperacion(cantidadConvertir);
                break;
            case "Peso mexicano a libra":
                setearDivisa("Libras", 0.045);
                resultadoConversion = realizarOperacion(cantidadConvertir);
                break;
            case "Dolar a peso mexicano":
                setearDivisa("Pesos mexicanos", 18.36);
                resultadoConversion = realizarOperacion(cantidadConvertir);
                break;
            case "Euro a peso mexicano":
                setearDivisa("Pesos mexicanos", 19.64);
                resultadoConversion = realizarOperacion(cantidadConvertir);
                break;
            case "Libra a peso mexicano":
                setearDivisa("Pesos mexicanos", 22.11);
                resultadoConversion = realizarOperacion(cantidadConvertir);
                break;
            default:
                resultadoConversion=0;
                break;
        }
        return resultadoConversion;
    }
    private void setearDivisa(String nombre, double valor){
        divisa.setNombre(nombre);
        divisa.setValor(valor);
    }
}
