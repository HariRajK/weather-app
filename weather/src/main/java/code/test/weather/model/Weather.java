package code.test.weather.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Weather {

	private String city;
	private LocalDateTime time;
	private String temp;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("timestamp_local")
	public LocalDateTime getTime() {
		return time;
	}

	@JsonSetter("timestamp_local")
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@JsonProperty("temp")
	public String getTemp() {
		return temp;
	}

	@JsonSetter("temp")
	public void setTemp(String temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "Weather [city=" + city + ", time=" + time + ", temp=" + temp + "]";
	}

}
