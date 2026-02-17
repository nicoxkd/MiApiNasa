package com.eusa.miapinasa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;


public class NasaApodClient {

    private static final String NASA_APOD_API_URL = "https://api.nasa.gov/planetary/apod";
    // Mi clave de API obtenida de la web oficial de la NASA
    private static final String API_KEY = "MHNyTFdrZyFGU1kdOZ2IpO3aclPMJGs9R3vgekjb";

    public JSONObject getApodData() throws IOException, JSONException {
        // Obtenemos una fecha al azar para cumplir el Objetivo 2 de la práctica
        String randomDate = getRandomDate();

        // Construcción de la URL metiendo la API KEY y el parámetro date solicitado
        String urlWithParams = NASA_APOD_API_URL + "?api_key=" + API_KEY + "&date=" + randomDate;

        // Configuramos la conexión HTTP por GET
        URL url = new URL(urlWithParams);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Si el servidor no responde 200 OK, lanzamos excepción
        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error en la solicitud: " + responseCode);
        }

        // Leemos el chorro de datos (Stream) que nos devuelve la NASA
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Transformamos el string acumulado en un objeto JSON para manejarlo fácil
        return new JSONObject(response.toString());
    }

    // Método para generar fechas aleatorias (YYYY-MM-DD) de los últimos 10 años.

    private String getRandomDate() {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();

        // Restamos un número de días al azar entre hoy y hace 10 años (3650 días)
        int randomDays = random.nextInt(3650);
        calendar.add(Calendar.DAY_OF_YEAR, -randomDays);

        // Formateamos la fecha al estilo que entiende la API de la NASA
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }
}