package com.example.noushad.weatherapp;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noushad.weatherapp.Retrofit.WeatherServiceProvider;
import com.example.noushad.weatherapp.events.ErrorEvent;
import com.example.noushad.weatherapp.events.WeatherEvent;
import com.example.noushad.weatherapp.model.WeatherForecast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.vstechlab.easyfonts.EasyFonts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, LocationListener,
        GoogleApiClient.OnConnectionFailedListener,ResultCallback<LocationSettingsResult> {

    private static final long UPDATE_INTERVAL = 3600000;
    private static final long FASTEST_INTERVAL = 600000;
    private WeatherServiceProvider weatherServiceProvider;
    @BindView(R.id.textView2)
    TextView mCelsiusText;
    @BindView(R.id.weather_temp)
    TextView mTemperatureView;
    @BindView(R.id.weather_summary)
    TextView mSummaryView;
    @BindView(R.id.weather_cover)
    ImageView mCoverImage;
    @BindView(R.id.weather_icon)
    ImageView mIconImage;
    @BindView(R.id.forecast_view)
    ConstraintLayout holdingView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.pritha_summury)
    TextView mPrithaText;


    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;
    private LocationRequest mLocationRequest;
    private final int LOCATION_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weatherServiceProvider = new WeatherServiceProvider();
        holdingView.setVisibility(View.GONE);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    }


    private void requestForecast(double lat, double lng) {
        weatherServiceProvider.requestWeatherForeCast(lat, lng);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent event) {
        mProgressBar.setVisibility(View.GONE);
        holdingView.setVisibility(View.VISIBLE);
        WeatherForecast forecast = event.getForecast();
        long fahrenheit = Math.round(forecast.getCurrently().getTemperature());
        long celsius = (fahrenheit - 32) / 9 * 5;
        setViewData(forecast, celsius);
    }

    private void setViewData(WeatherForecast forecast, long celsius) {
        mTemperatureView.setText(String.valueOf(celsius));
        mTemperatureView.setTypeface(EasyFonts.caviarDreams(this));
        mSummaryView.setText(forecast.getCurrently().getSummary());
        mSummaryView.setTypeface(EasyFonts.caviarDreams(this));
        mCelsiusText.setTypeface(EasyFonts.caviarDreams(this));
        mPrithaText.setTypeface(EasyFonts.caviarDreams(this));
        switchWeatherImage(forecast);
    }

    private void switchWeatherImage(WeatherForecast forecast) {
        String weather = forecast.getCurrently().getIcon();
        switch (weather) {
            case "clear-day":
                mCoverImage.setImageResource(R.drawable.clear_sky_day);
                mIconImage.setImageResource(R.drawable.ic_clear_day);
                break;
            case "clear-night":
                mCoverImage.setImageResource(R.drawable.clear_sky_night);
                mIconImage.setImageResource(R.drawable.ic_clear_night);
                break;
            case "rain":
                mCoverImage.setImageResource(R.drawable.rain);
                mIconImage.setImageResource(R.drawable.ic_rain);
                break;
            case "sleet":
                mCoverImage.setImageResource(R.drawable.sleet);
                mIconImage.setImageResource(R.drawable.ic_sleet);
                break;
            case "wind":
                mCoverImage.setImageResource(R.drawable.cloudy);
                mIconImage.setImageResource(R.drawable.ic_cloudy);
                break;
            case "fog":
                mCoverImage.setImageResource(R.drawable.fog_2);
                mIconImage.setImageResource(R.drawable.ic_fog);
                break;
            case "cloudy":
                mCoverImage.setImageResource(R.drawable.cloudy);
                mIconImage.setImageResource(R.drawable.ic_cloudy);
                break;
            case "partly_cloudy_day":
                mCoverImage.setImageResource(R.drawable.partly_cloudy);
                mIconImage.setImageResource(R.drawable.ic_partly_cloudy_day);
                break;
            case "partly-cloudy-night":
                mCoverImage.setImageResource(R.drawable.partly_cloudy_night);
                mIconImage.setImageResource(R.drawable.ic_partly_cloudy_night);
                break;
            case "thunderstorm":
            case "hail":
            case "tornado":
                mCoverImage.setImageResource(R.drawable.thunder);
                mIconImage.setImageResource(R.drawable.ic_thunderstorm);
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        mProgressBar.setVisibility(View.GONE);
        String s = event.getErrorMessage();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION);
            return;
        }
        startLocationUpdates();
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation == null) {
            startLocationUpdates();
        }
        if (mLocation != null) {
            requestForecast(mLocation.getLatitude(), mLocation.getLongitude());
        } else {
            showActivationDialog();
        }
    }

    private void showActivationDialog() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        builder.build()
                );

        result.setResultCallback(this);
    }

    protected void startLocationUpdates() {
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION);
            showActivationDialog();
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        requestForecast(location.getLatitude(), location.getLongitude());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case LOCATION_PERMISSION :
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    startLocationUpdates();
                }

        }
    }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:

                break;

            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                try {

                    status.startResolutionForResult(MainActivity.this, LOCATION_PERMISSION);

                } catch (IntentSender.SendIntentException e) {
                }
                break;

            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                break;
        }
    }
}

