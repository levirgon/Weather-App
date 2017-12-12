package com.example.noushad.weatherapp.events;

import com.example.noushad.weatherapp.model.WeatherForecast;

/**
 * Created by noushad on 10/27/17.
 */

public class WeatherEvent {

    private final WeatherForecast forecast;

    public WeatherEvent(WeatherForecast forecast) {
        this.forecast = forecast;
    }

    public WeatherForecast getForecast() {
        return forecast;
    }
}
