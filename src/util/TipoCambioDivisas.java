package util;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TipoCambioDivisas {
    private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=2d17460915104431b3d0ca4a9e2c6606";
    public static Map<String, Double> obtenerTipoCambioDivisas(){
        String response = obtenerJsonAPI();
        JSONObject jsonObject = new JSONObject(response);
        JSONObject rates = jsonObject.getJSONObject("rates");
//Convertir el JSON en un Map<String, Double>
        Map<String, Double> tiposDeCambioBaseUsd = new HashMap<>();
        Iterator<String> keys = rates.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            double value = rates.getDouble(key);
            double roundedValue = Math.round(value * 100.0) / 100.0; // Redondear a dos decimales
            tiposDeCambioBaseUsd.put(key, roundedValue);
        }
        return tiposDeCambioBaseUsd;
    }

    private static String obtenerJsonAPI(){
        StringBuffer response;
        try{
            String inputLine;
            URL url = new URL(API_URL);//MalformedURLException
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//IOException
            conn.setRequestMethod("GET");
            /*
             *1.-El servidor contesta con un InputStream por lo cual se crea un objeto InputStreamReader pasando al constructor lo que contesto el servidor
             *2.-Con base en el objeto creado InputStreamReader se crea un objeto BufferedReader para poder leer el contenido linea por linea
             *Resumen: Esto es una forma de leer la respuesta del servidor en Java mediante HttpURLConnection.
             */
            try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
                /*
                 *Este objeto StringBuffer es como un String pero mutable
                 *Poder trabajar con las lineas de la respuesta del servidor y concatenarlas en un mismo String
                 */
                response = new StringBuffer();
                /*
                 *BufferedREader.readLine() retorna un String con la linea actual del BufferedReader o null si ya no hay mas lineas
                 *la linea actual de la respuesta del servidor se le asigna a inputLine y cuando a inputLine se le asigne null la condicion
                 *ya no se cumplira y terminara el ciclo. La condicion es que mietras inputLine = in.readLine() sea diferente de null
                 * seguira el ciclo
                 */
                while ((inputLine = in.readLine()) != null) {//
                    response.append(inputLine);
                }
            }
        }catch (IOException e){
            System.out.println("Ocurrio un error en la conexion a la API");
            response= new StringBuffer();
        }
        return response.toString();
    }


}
