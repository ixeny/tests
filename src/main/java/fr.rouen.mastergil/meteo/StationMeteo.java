package fr.rouen.mastergil.meteo;


import org.codehaus.jettison.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudy on 26/02/17.
 */
public class StationMeteo {

    private IWeatherProvider weatherProvider;


    public StationMeteo(IWeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public static void main(String[] args) throws JSONException {
        System.out.println(
                new StationMeteo(new OpenWeatherMapProvider())
                        .majPrevision("Paris,FR")
        );
    }

    public List<Prevision> majPrevision(String city) throws JSONException {
        if (null == city) {
            throw new IllegalArgumentException("City is not optional");
        }
        final List<Prevision> previsions = new ArrayList<Prevision>();

        return weatherProvider.getForecastByCity(city);
    }
}
