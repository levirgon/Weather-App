package com.example.noushad.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class WeatherForecast{

	@SerializedName("currently")
	private Currently currently;

	@SerializedName("offset")
	private double offset;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("daily")
	private Daily daily;

	@SerializedName("flags")
	private Flags flags;

	@SerializedName("hourly")
	private Hourly hourly;

	@SerializedName("minutely")
	private Minutely minutely;

	@SerializedName("longitude")
	private double longitude;

	public Currently getCurrently(){
		return currently;
	}

	public double getOffset(){
		return offset;
	}

	public String getTimezone(){
		return timezone;
	}

	public double getLatitude(){
		return latitude;
	}

	public Daily getDaily(){
		return daily;
	}

	public Flags getFlags(){
		return flags;
	}

	public Hourly getHourly(){
		return hourly;
	}

	public Minutely getMinutely(){
		return minutely;
	}

	public double getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"WeatherForecast{" + 
			"currently = '" + currently + '\'' + 
			",offset = '" + offset + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",daily = '" + daily + '\'' + 
			",flags = '" + flags + '\'' + 
			",hourly = '" + hourly + '\'' + 
			",minutely = '" + minutely + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}