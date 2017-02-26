package fr.rouen.mastergil.meteo;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by rudy on 26/02/17.
 */
public class OpenWeatherMapProvider implements IWeatherProvider {


    private final static String URL_BASE = "http://api.openweathermap.org/data/2.5";


    @Override
    public List<Prevision> getForecastByCity(String city) {
        List<Prevision> previsions = new ArrayList<Prevision>();
        ClientConfig clientConfig = new DefaultClientConfig();

        clientConfig.getFeatures().put(
                JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        WebResource webResource = client
                .resource(URL_BASE).path("forecast/daily?APPID=661608d780618193596c7321268a4717&lang=fr&units=metric&cnt=12&mode=json&q=" + city);
        ClientResponse response = webResource.accept("application/json")
                .type("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        try {
            final JSONObject forecastJson = new JSONObject(response.getEntity(String.class));

            final JSONArray list = forecastJson.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                final Prevision prevision = new Prevision();
                final JSONObject prevJSON = list.getJSONObject(i);
                final JSONObject temp = prevJSON.getJSONObject("temp");
                prevision.setDate(new Date(prevJSON.getLong("dt") * 1000));
                prevision.setTempMin(temp.getDouble("min"));
                prevision.setTempMax(temp.getDouble("max"));
                prevision.setTempDay(temp.getDouble("day"));
                prevision.setTempNight(temp.getDouble("night"));
                prevision.setDescription(prevJSON.getJSONArray("weather").getJSONObject(0).getString("description"));
                previsions.add(prevision);
            }
        } catch (JSONException jse) {

        }
        return previsions;
    }
}
