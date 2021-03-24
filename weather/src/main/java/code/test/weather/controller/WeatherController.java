package code.test.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import code.test.weather.model.Weather;
import code.test.weather.service.WeatherService;
import code.test.weather.validation.ValidateZipCode;

@RestController
@Validated
public class WeatherController {

	@Autowired
	WeatherService service;

	@GetMapping("/weather/{zipcode}")
	public Weather getWeather(@ValidateZipCode @PathVariable String zipcode) {
		return service.getTommorowWeatherForecast(zipcode);
	}

}
