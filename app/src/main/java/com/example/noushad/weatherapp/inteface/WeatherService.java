package com.example.noushad.weatherapp.inteface;

import com.example.noushad.weatherapp.model.WeatherForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by noushad on 10/26/17.
 */

public interface WeatherService {

@GET("{lat},{lng}")
    Call<WeatherForecast> getWeatherForecast(@Path("lat") double lat, @Path("lng") double lng);
}
