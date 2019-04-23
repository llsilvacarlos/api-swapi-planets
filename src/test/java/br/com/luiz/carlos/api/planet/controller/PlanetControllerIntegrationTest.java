package br.com.luiz.carlos.api.planet.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.luiz.carlos.api.planet.dto.PlanetDTO;
import br.com.luiz.carlos.api.planet.gateway.planet.dto.PlanetSwapiDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetControllerIntegrationTest {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		
		PlanetDTO planetDTO = new PlanetDTO.Builder().setName("Terra").setWeather("Quente").build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<PlanetDTO> entity = new HttpEntity<>(planetDTO, headers);

		template.postForEntity(base.toString().concat("add"), entity,
				PlanetDTO.class);
		
		new PlanetDTO.Builder().setName("Jupter").setWeather("Quente").build();
		entity = new HttpEntity<>(planetDTO, headers);
		template.postForEntity(base.toString().concat("add"), entity,
				PlanetDTO.class);
		
		
	}

	@Test
	public void shouldPostPlanetSucessTest() throws Exception {
		PlanetDTO planetDTO = new PlanetDTO.Builder().setName("Terra").setWeather("Quente").build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<PlanetDTO> entity = new HttpEntity<>(planetDTO, headers);

		ResponseEntity<PlanetDTO> response = template.postForEntity(base.toString().concat("add"), entity,
				PlanetDTO.class);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertNotNull(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void shoulFindAllTest() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);


		ResponseEntity<PlanetDTO[]> response = template.getForEntity(base.toString(), PlanetDTO[].class);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertFalse(Arrays.asList(response.getBody()).isEmpty());
		assertNotNull(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}
	
	@Test
	public void shoulFindAllApiTest() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);


		ResponseEntity<PlanetSwapiDTO[]> response = template.getForEntity(base.toString().concat("/api"), PlanetSwapiDTO[].class);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertFalse(Arrays.asList(response.getBody()).isEmpty());
		assertNotNull(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}
	
	

	@Test
	public void shoulFindByNameTest() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);


		ResponseEntity<PlanetDTO> response = template.getForEntity(base.toString().concat("/name/Terra"), PlanetDTO.class);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertNotNull(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}
	
	@Test
	public void shoulFindByIdTest() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		
		PlanetDTO planetDTO = new PlanetDTO.Builder().setName("Marte").setWeather("Quente").build();
		HttpEntity<PlanetDTO> entity = new HttpEntity<>(planetDTO, headers);

		planetDTO= template.postForEntity(base.toString().concat("add"), entity,
				PlanetDTO.class).getBody();
		

		ResponseEntity<PlanetDTO> response = template.getForEntity(base.toString().concat(""+planetDTO.getId()), PlanetDTO.class);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertNotNull(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}
	
	
	@Test
	public void shoulRemoveTest() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		
		PlanetDTO planetDTO = new PlanetDTO.Builder().setName("Terra").setWeather("Quente").build();
		HttpEntity<PlanetDTO> entity = new HttpEntity<>(planetDTO, headers);

		planetDTO= template.postForEntity(base.toString().concat("add"), entity,
				PlanetDTO.class).getBody();
		

		template.delete(base.toString().concat("/id/"+planetDTO.getId()));
		

	}

}
