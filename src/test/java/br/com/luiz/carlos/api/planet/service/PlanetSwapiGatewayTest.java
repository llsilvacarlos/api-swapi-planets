package br.com.luiz.carlos.api.planet.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.luiz.carlos.api.planet.gateway.planet.PlanetSwapiGateway;
import br.com.luiz.carlos.api.planet.gateway.planet.dto.PlanetSwapiDTO;
import br.com.luiz.carlos.api.planet.gateway.planet.impl.PlanetSwapiGatewayImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class PlanetSwapiGatewayTest {

	@Configuration
	static class Config {

		@Bean
		public PlanetSwapiGateway planetSwapiGatewat() {
			PlanetSwapiGateway planetSwapiGateway = new PlanetSwapiGatewayImpl();
			return planetSwapiGateway;
		}
	}
	
	@Before
	public void init(){
		//loadCache
		 planetSwapiGateway.findAll();
	}

	@Autowired
	private PlanetSwapiGateway planetSwapiGateway;

	@Test
	public void shouldFindAll() {

		List<PlanetSwapiDTO> listPlanetSwapiDTO = planetSwapiGateway.findAll();

		assertNotNull(listPlanetSwapiDTO);
		assertFalse(listPlanetSwapiDTO.isEmpty());


	}
	
	@Test
	public void shouldFindByName() {
		PlanetSwapiDTO planetSwapiDTO =  planetSwapiGateway.findByName("Alderaan");
		assertNotNull(planetSwapiDTO);

	}
	
	
	@Test
	public void shouldFindByNameNotFound() {
		PlanetSwapiDTO planetSwapiDTO =  planetSwapiGateway.findByName("Terra");
		assertNull(planetSwapiDTO);

	}
	
	
	@Test
	public void shouldFindByNameFoundTestUpperCase() {
		PlanetSwapiDTO planetSwapiDTO =  planetSwapiGateway.findByName("ALDERAAN");
		assertNotNull(planetSwapiDTO);

	}

}
