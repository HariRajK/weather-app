package code.test.weather.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import code.test.weather.exception.InvalidZipCodeException;
import code.test.weather.model.Weather;
import code.test.weather.model.WeatherDetail;

@Service
public class WeatherService {

	@Value("${app.url}")
	private String url;

	@Value("${app.api.key}")
	private String apiKey;

	LocalDate localDate = LocalDate.now();

	public Weather getTommorowWeatherForecast(String zipCode) {

		ResponseEntity<WeatherDetail> responseEntity = executeRest(zipCode, WeatherDetail.class);
		List<Weather> filterWeatherList = responseEntity.getBody().getData().stream().filter(weather -> weather.getTime().toLocalDate().equals(localDate.plusDays(1))).collect(Collectors.toList());
		filterWeatherList.sort((Weather w1, Weather w2) -> Double.compare(Double.parseDouble(w1.getTemp()),
				Double.parseDouble(w2.getTemp())));
		
		filterWeatherList.get(0).setCity(responseEntity.getBody().getCityName());

		return filterWeatherList.get(0);

	}

	private ResponseEntity<WeatherDetail> executeRest(String zipCode, Class<WeatherDetail> responseType) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(this.url).queryParam("postal_code", zipCode)
				.queryParam("key", this.apiKey).queryParam("country", "US");
		String uriString = uri.toUriString();
		RequestEntity<?> request = RequestEntity.get(uriString).build();
		return restTemplate.exchange(request, responseType);
	}

}
