import javax.swing.*;

public class Prueba {

    static String [] opcionesTipoConversor = {"Conversor de monedas", "Otro conversor"};
    static double cantidadConvertir = 0.0;
    static String [] opcionesConversion = {"Peso mexicano a dolar", "Peso mexicano a euro","Peso mexicano a libra","Dolar a peso mexicano", "Euro a peso mexicano",
            "Libra a peso mexicano"};
    static double valorPesoPorCadaDolar = 18.36;
    static double valorPesoPorCadaEuro = 19.64;
    static double valorPesoPorCadaLibra = 22.11;
    static double resultadoConversion = 0;
    public static void main(String[] args) {
        //Pedir que el usuario seleccione el tipo de conversor ej de moneda, de medicion, etc
        String opcion = (JOptionPane.showInputDialog(null,"Seleccione una opcion de conversion",
                "Menu",JOptionPane.QUESTION_MESSAGE,null, opcionesTipoConversor, opcionesTipoConversor[0]).toString());

        //Si el valor ingresado es menor que cero o el valor no es numerico, le volvera a mostrar el modal para que ingrese la cantidad
        while (cantidadConvertir<=0){
            cantidadConvertir = convertirStringCantidadDouble();
        }

        //Pedir el tipo de conversion ej peso a dolar, dolar a peso, etc
        String tipoConversion = (JOptionPane.showInputDialog(null,"Elige el tipo de conversion","Monedas", JOptionPane.QUESTION_MESSAGE,null,
                        opcionesConversion,opcionesConversion[0]).toString());

        switch (tipoConversion){
            case "Peso mexicano a dolar":
                resultadoConversion = cantidadConvertir / valorPesoPorCadaDolar;
                break;
            case "Peso mexicano a euro":
                resultadoConversion = cantidadConvertir / valorPesoPorCadaEuro;
                break;
            case "Peso mexicano a libra":
                resultadoConversion = cantidadConvertir / valorPesoPorCadaLibra;
                break;
            case "Dolar a peso mexicano":
                resultadoConversion = cantidadConvertir * valorPesoPorCadaDolar;
                break;
            case "Euro a peso mexicano":
                resultadoConversion = cantidadConvertir * valorPesoPorCadaEuro;
                break;
            case "Libra a peso mexicano":
                resultadoConversion = cantidadConvertir * valorPesoPorCadaLibra;
                break;
            default:
                resultadoConversion=0;
                break;
        }

        JOptionPane.showMessageDialog(null, tipoConversion + "\n" + cantidadConvertir +
                " = " + resultadoConversion,"Conversion",JOptionPane.INFORMATION_MESSAGE);

    }
    public static double convertirStringCantidadDouble(){
        try{
            cantidadConvertir = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la cantidad que desea convertir, solo valores mayores a cero"));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Solo se pueden ingresar valores numericos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return cantidadConvertir;
    }
}
