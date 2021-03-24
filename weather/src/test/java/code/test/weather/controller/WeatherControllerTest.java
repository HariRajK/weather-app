package code.test.weather.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import code.test.weather.exception.InvalidZipCodeException;
import code.test.weather.model.Weather;
import code.test.weather.service.WeatherService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = WeatherController.class)
public class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;

	@InjectMocks
	private WeatherController weatherController;

	@Test
	void validZipCode() throws Exception {

		Weather weatherInformation = new Weather();
		when(weatherService.getTommorowWeatherForecast("12345")).thenReturn(weatherInformation);

		Assert.assertEquals(weatherInformation, weatherController.getWeather("12345"));

		mockMvc.perform(get("/weather/{zipcode}", "12345")).andExpect(status().isOk());
	}

	@Test
	void invalidZipCode() throws Exception {
		when(weatherService.getTommorowWeatherForecast("12345")).thenThrow(new InvalidZipCodeException());
		Assertions.assertThrows(InvalidZipCodeException.class, () -> weatherController.getWeather("12345"));
		mockMvc.perform(get("/weather/{zipcode}", "12345")).andExpect(status().isNotFound());
	}

	@Test
	void incorrectZipCode() throws Exception {
		mockMvc.perform(get("/weather/{zipcode}", "123")).andExpect(status().isBadRequest());
	}

	@Test
	void noZipCode() throws Exception {
		mockMvc.perform(get("/weather/{zipcode}", "")).andExpect(status().isNotFound());
	}

}
