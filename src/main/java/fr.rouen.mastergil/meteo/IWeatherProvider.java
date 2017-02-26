package fr.rouen.mastergil.meteo;

/**
 * Created by rudy on 26/02/17.
 */

import java.util.List;

public interface IWeatherProvider {

    List<Prevision> getForecastByCity(String city);
}
