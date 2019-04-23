package br.com.luiz.carlos.api.planet.gateway.planet.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.JsonPath;

import br.com.luiz.carlos.api.planet.Application;
import br.com.luiz.carlos.api.planet.gateway.planet.PlanetSwapiGateway;
import br.com.luiz.carlos.api.planet.gateway.planet.dto.PlanetSwapiDTO;

@Component
public class PlanetSwapiGatewayImpl implements PlanetSwapiGateway {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);


	private Map<Long, PlanetSwapiDTO> mapPlanetSwapiDto = new HashMap<>();

	@Override
	@Cacheable("palnets")
	public List<PlanetSwapiDTO> findAll() {
		try {
			loadFilms();
		} catch (ClientProtocolException e) {
			LOG.error("Erro ao acessar a API SWAPI", e);
		} catch (IOException e) {
			LOG.error("Erro ao acessar a API SWAPI", e);
		}
		return new ArrayList<PlanetSwapiDTO>(mapPlanetSwapiDto.values());

	}

	@Override
	public PlanetSwapiDTO findByName(String name) {
		return this.mapPlanetSwapiDto.values().stream().filter(a -> a.getName().equalsIgnoreCase(name)).findAny()
				.orElse(null);
	}

	public void loadFilms() throws ClientProtocolException, IOException {

		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpGet getRequest = new HttpGet("http://swapi.co/api/films/");

		getRequest.addHeader("accept", "application/json");
		getRequest.addHeader("Content-Type", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		// verify the valid error code first
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}

		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		List<String> listURIPlanets = JsonPath.parse(apiOutput).read("$.results[*].planets[*]");
		loadPlanets(listURIPlanets);

	}

	private void loadPlanets(List<String> listURIPlanets) throws ParseException, IOException {

		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpGet getRequest;
		HttpResponse response;
		int statusCode;
		String apiOutput;
		for (String urlPlanets : listURIPlanets) {

			Long id = Long
					.parseLong(urlPlanets.substring("https://swapi.co/api/planets/".length(), urlPlanets.length() - 1));

			getRequest = new HttpGet(urlPlanets);
			getRequest.addHeader("accept", "application/json");
			getRequest.addHeader("Content-Type", "application/json");

			response = httpClient.execute(getRequest);

			statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}

			HttpEntity httpEntity = response.getEntity();
			apiOutput = EntityUtils.toString(httpEntity);
			String name = JsonPath.parse(apiOutput).read("$.name");

			if (!mapPlanetSwapiDto.containsKey(id)) {
				PlanetSwapiDTO planetSwapiDTO = new PlanetSwapiDTO.Builder().setId(id).setName(name).setQuantity(1)
						.build();
				mapPlanetSwapiDto.put(id, planetSwapiDTO);
			} else {
				PlanetSwapiDTO planetSwapiDTO = mapPlanetSwapiDto.get(id);
				planetSwapiDTO.setQuantity(planetSwapiDTO.getQuantity() + 1);

			}

		}
	}

	

}
