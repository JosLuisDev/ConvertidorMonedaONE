package util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TipoCambioDivisas {
    private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=2d17460915104431b3d0ca4a9e2c6606";

    public static Map<String, Double> obtenerTipoCambioDivisas() {
        try {
            String response = obtenerJsonAPI();
            JSONObject jsonObject = new JSONObject(response);
            JSONObject rates = jsonObject.getJSONObject("rates");
            return convertirJsonAMap(rates);
        } catch (IOException e) {
            System.out.println("Ocurrió un error en la conexión a la API: " + e.getMessage());
            return new HashMap<>();
        }
    }

    private static Map<String, Double> convertirJsonAMap(JSONObject jsonObject) {
        Map<String, Double> tiposDeCambioBaseUsd = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            double value = jsonObject.getDouble(key);
            double roundedValue = Math.round(value * 100.0) / 100.0;
            tiposDeCambioBaseUsd.put(key, roundedValue);
        }
        return tiposDeCambioBaseUsd;
    }

    private static String obtenerJsonAPI() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }
}