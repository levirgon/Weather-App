package com.example.noushad.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Currently{

	@SerializedName("summary")
	private String summary;

	@SerializedName("precipProbability")
	private double precipProbability;

	@SerializedName("visibility")
	private double visibility;

	@SerializedName("windGust")
	private double windGust;

	@SerializedName("precipIntensity")
	private double precipIntensity;

	@SerializedName("icon")
	private String icon;

	@SerializedName("cloudCover")
	private double cloudCover;

	@SerializedName("windBearing")
	private double windBearing;

	@SerializedName("apparentTemperature")
	private double apparentTemperature;

	@SerializedName("pressure")
	private double pressure;

	@SerializedName("dewPoint")
	private double dewPoint;

	@SerializedName("ozone")
	private double ozone;

	@SerializedName("nearestStormBearing")
	private double nearestStormBearing;

	@SerializedName("nearestStormDistance")
	private double nearestStormDistance;

	@SerializedName("temperature")
	private double temperature;

	@SerializedName("humidity")
	private double humidity;

	@SerializedName("time")
	private double time;

	@SerializedName("windSpeed")
	private double windSpeed;

	@SerializedName("uvIndex")
	private double uvIndex;

	public String getSummary(){
		return summary;
	}

	public double getPrecipProbability(){
		return precipProbability;
	}

	public double getVisibility(){
		return visibility;
	}

	public double getWindGust(){
		return windGust;
	}

	public double getPrecipIntensity(){
		return precipIntensity;
	}

	public String getIcon(){
		return icon;
	}

	public double getCloudCover(){
		return cloudCover;
	}

	public double getWindBearing(){
		return windBearing;
	}

	public double getApparentTemperature(){
		return apparentTemperature;
	}

	public double getPressure(){
		return pressure;
	}

	public double getDewPoint(){
		return dewPoint;
	}

	public double getOzone(){
		return ozone;
	}

	public double getNearestStormBearing(){
		return nearestStormBearing;
	}

	public double getNearestStormDistance(){
		return nearestStormDistance;
	}

	public double getTemperature(){
		return temperature;
	}

	public double getHumidity(){
		return humidity;
	}

	public double getTime(){
		return time;
	}

	public double getWindSpeed(){
		return windSpeed;
	}

	public double getUvIndex(){
		return uvIndex;
	}

	@Override
 	public String toString(){
		return 
			"Currently{" + 
			"summary = '" + summary + '\'' + 
			",precipProbability = '" + precipProbability + '\'' + 
			",visibility = '" + visibility + '\'' + 
			",windGust = '" + windGust + '\'' + 
			",precipIntensity = '" + precipIntensity + '\'' + 
			",icon = '" + icon + '\'' + 
			",cloudCover = '" + cloudCover + '\'' + 
			",windBearing = '" + windBearing + '\'' + 
			",apparentTemperature = '" + apparentTemperature + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",dewPoint = '" + dewPoint + '\'' + 
			",ozone = '" + ozone + '\'' + 
			",nearestStormBearing = '" + nearestStormBearing + '\'' + 
			",nearestStormDistance = '" + nearestStormDistance + '\'' + 
			",temperature = '" + temperature + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",time = '" + time + '\'' + 
			",windSpeed = '" + windSpeed + '\'' + 
			",uvIndex = '" + uvIndex + '\'' + 
			"}";
		}
}