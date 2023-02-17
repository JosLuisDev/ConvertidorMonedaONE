import conversordivisas.ConversorDivisas;

import javax.swing.*;

public class ProgramaConversor {
    private static String [] opcionesTipoConversor = {"Conversor de monedas", "Otro conversor"};
    private static String opcion;
    public static void correr(){
        double cantidadConvertir = 0;
        //Pedir que el usuario seleccione el tipo de conversor ej de moneda, de medicion, etc
        opcion = (JOptionPane.showInputDialog(null,"Seleccione una opcion de conversion",
                "Menu",JOptionPane.QUESTION_MESSAGE,null, opcionesTipoConversor, opcionesTipoConversor[0]).toString());

        //Si el valor ingresado es menor que cero o el valor no es numerico, le volvera a mostrar el modal para que ingrese la cantidad
        while (cantidadConvertir<=0){
            cantidadConvertir = convertirStringCantidadDouble(cantidadConvertir);
        }

        switch (opcion){
            case "Conversor de monedas":
                ConversorDivisas cd = new ConversorDivisas();
                cd.convertirDivisas(cantidadConvertir);
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
