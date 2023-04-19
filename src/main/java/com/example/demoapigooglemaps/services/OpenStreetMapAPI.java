package com.example.demoapigooglemaps.services;

import com.example.demoapigooglemaps.model.Coordenate;
import com.example.demoapigooglemaps.model.Address;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OpenStreetMapAPI {

    private static String URL_COORDENADAS_API = "https://nominatim.openstreetmap.org/search?";
    private static String URL_RUTA_COORDENADAS_API = "https://router.project-osrm.org/route/v1/driving/";

    private static final HttpClient httpClient = HttpClientBuilder.create().build();

    public static void getCoordenates(Address address) throws IOException, URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder(URL_COORDENADAS_API)
                .addParameter("q", address.toString())
                .addParameter("format", "json")
                .addParameter("limit", "1");

        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(new HttpGet(uriBuilder.build()))) {
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(responseBody);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                Coordenate coordenate = new Coordenate(jsonObject.getBigDecimal("lat"), jsonObject.getBigDecimal("lon"));
                address.setCoordenate(coordenate);
            }
        }
    }

    public static List<Coordenate> getRoutesCoordinates(String origin, String destination)
            throws IOException, URISyntaxException {

        List<Coordenate> coordinates = new ArrayList<Coordenate>();
        URIBuilder uriBuilder = new URIBuilder(URL_RUTA_COORDENADAS_API.concat(origin).concat(";").concat(destination))
                .addParameter("steps", "true");
        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(new HttpGet(uriBuilder.build()))) {
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            JSONArray jsonSteps = new JSONObject(responseBody).getJSONArray("routes")
                    .getJSONObject(0)
                    .getJSONArray("legs")
                    .getJSONObject(0)
                    .getJSONArray("steps");

            jsonSteps.forEach(jsonStep -> {
                JSONArray jsonIntersections = ((JSONObject) jsonStep).getJSONArray("intersections");
                jsonIntersections.forEach(jsonIntersection -> {
                    JSONArray jsonLocation = ((JSONObject) jsonIntersection).getJSONArray("location");
                    coordinates.add(new Coordenate(jsonLocation.getBigDecimal(1), jsonLocation.getBigDecimal(0)));
                });
            });

            return coordinates;
        }

    }
}
