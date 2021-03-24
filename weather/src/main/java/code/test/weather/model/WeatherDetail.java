package code.test.weather.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherDetail {
	
	private List<Weather> data = new ArrayList<>();
	private String cityName;
	private String countryCode;

	@JsonProperty("data")
	public List<Weather> getData() {
		return this.data;
	}

	@JsonSetter("data")
	public void setData(List<Weather> data) {
		this.data = data;
	}
	
	@JsonProperty("city_name")
	public String getCityName() {
		return cityName;
	}
	
	@JsonSetter("city_name")
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@JsonProperty("country_code")
	public String getCountryCode() {
		return countryCode;
	}
	
	@JsonSetter("country_code")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	

}
