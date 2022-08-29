package br.com.bobhome.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bobhome.entities.PlanetTO;
import br.com.bobhome.entities.ResponsePlanetTO;

@Service
public class SwapiPlanetService {

	private static final String URL_PLANETS = "https://swapi.dev/api/planets";
	private static final Logger LOGGER = LogManager.getLogger();

	public int findQuantityOfApparitionsInMovies(String nome) {
		PlanetTO planetTO = findPlanetByName(nome);
		return planetTO != null && planetTO.getFilms() != null ? planetTO.getFilms().size() : 0;
	}
	
	public PlanetTO findPlanetByName(String name) {
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(URL_PLANETS)
		.queryParam("search", "{search}")
		.queryParam("format", "{format}")
		.encode().toUriString();
		Map<String, String> params = new HashMap<String, String>();
		params.put("search", name);
		params.put("format", "json");
		
		try {
			ResponsePlanetTO responsePlanetTO = new RestTemplate().getForObject(urlTemplate, ResponsePlanetTO.class, params);
			return responsePlanetTO != null && !CollectionUtils.isEmpty(responsePlanetTO.getResults()) ? responsePlanetTO.getResults().get(0) : null;
		} catch(RestClientException e) {
			LOGGER.error("Error to find planets by name '{}'", name, e);
			return null;
		}
	}
	
}
