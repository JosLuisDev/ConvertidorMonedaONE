import conversordivisas.ConversorDivisas;

import javax.swing.*;

public class ProgramaConversor {
    private static String [] opcionesTipoConversor = {"Conversor de monedas", "Otro conversor"};
    private static String opcion;
    private static int continuar = 0;
    private static final ConversorDivisas CONVERSOR_DIVISAS = new ConversorDivisas();
    public static void correr(){
        double cantidadConvertir = 0;
        while (continuar ==0){ //Al dar en no o cancel se detiene el programa
            //Pedir que el usuario seleccione el tipo de conversor ej de moneda, de medicion, etc
            opcion = obtenerOpcionSeleccionada();

            //Si el valor ingresado es menor que cero o el valor no es numerico, le volvera a mostrar el modal para que ingrese la cantidad
            while (cantidadConvertir<=0){
                cantidadConvertir = convertirStringCantidadDouble(cantidadConvertir);
            }
            validarTipoConversor(cantidadConvertir);
            cantidadConvertir=0;//Reset cantidad para que la vuelva a solicitar
            continuar = JOptionPane.showConfirmDialog(null, "Deseas continuar?");
        }
        JOptionPane.showMessageDialog(null, "Programa Terminado");
    }

    private static String obtenerOpcionSeleccionada(){
        return (String) JOptionPane.showInputDialog(null,"Seleccione una opcion de conversion",
                "Menu",JOptionPane.QUESTION_MESSAGE,null, opcionesTipoConversor, opcionesTipoConversor[0]);
    }

    private static void validarTipoConversor(double cantidadConvertir){
        switch (opcion){
            case "Conversor de monedas":
                CONVERSOR_DIVISAS.convertirDivisas(cantidadConvertir);
                break;
            default:
                JOptionPane.showMessageDialog(null,"Conversor no disponible por el momento");
                break;
        }
    }

    private static double convertirStringCantidadDouble(double cantidadConvertir){
        try{
            cantidadConvertir = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la cantidad que desea convertir, solo valores mayores a cero"));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Solo se pueden ingresar valores numericos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return cantidadConvertir;
    }
}
