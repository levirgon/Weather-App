package com.example.noushad.weatherapp.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Flags{

	@SerializedName("sources")
	private List<String> sources;

	@SerializedName("isd-stations")
	private List<String> isdStations;

	@SerializedName("units")
	private String units;

	public List<String> getSources(){
		return sources;
	}

	public List<String> getIsdStations(){
		return isdStations;
	}

	public String getUnits(){
		return units;
	}

	@Override
 	public String toString(){
		return 
			"Flags{" + 
			"sources = '" + sources + '\'' + 
			",isd-stations = '" + isdStations + '\'' + 
			",units = '" + units + '\'' + 
			"}";
		}
}