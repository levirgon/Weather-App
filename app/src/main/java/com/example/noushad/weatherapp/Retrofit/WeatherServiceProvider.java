package com.example.noushad.weatherapp.Retrofit;

import android.util.Log;

import com.example.noushad.weatherapp.events.ErrorEvent;
import com.example.noushad.weatherapp.events.WeatherEvent;
import com.example.noushad.weatherapp.inteface.WeatherService;
import com.example.noushad.weatherapp.model.WeatherForecast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by noushad on 10/26/17.
 */

public class WeatherServiceProvider {

    private static final WeatherService mService = ServiceGenerator.createService(WeatherService.class);

    private static final String TAG = "WeatherServiceProvider";

    public void requestWeatherForeCast(double lat, double lng) {

        Call<WeatherForecast> forecastCall = mService.getWeatherForecast(lat, lng);

        forecastCall.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if (response.isSuccessful()) {
                    WeatherForecast forecast = response.body();
                    EventBus.getDefault().post(new WeatherEvent(forecast));
                    Log.d(TAG, "onResponse: Successful :" + forecast.toString());
                } else {
                    try {
                        Log.d(TAG, "onResponse: Failed :" + response.errorBody().string());
                        EventBus.getDefault().post(new ErrorEvent("Error Occurred!!"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
            }
        });
    }

}
