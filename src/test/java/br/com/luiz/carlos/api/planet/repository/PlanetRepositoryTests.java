package br.com.luiz.carlos.api.planet.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.luiz.carlos.api.planet.domain.Planet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetRepositoryTests {

	@Autowired
	PlanetRepository repository;

	@Test
	public void shouldSavePlanet() {

		Planet planet = new Planet.Builder().setName("Terra").setWeather("temperate").build();

		planet = repository.save(planet);

		assertThat(planet.getId()).isNotNull();
		assertThat(planet.getName()).isNotNull();
		assertThat(planet.getWeather()).isNotNull();
	}

	@Test
	public void shouldSavePlanetAndFind() {

		Planet planet = new Planet.Builder().setName("Terra").setWeather("temperate").build();

		planet = repository.save(planet);

		assertThat(planet.getId()).isNotNull();
		assertThat(planet.getName()).isNotNull();
		assertThat(planet.getWeather()).isNotNull();
		Optional<Planet> search = repository.findById(planet.getId());

		assertTrue(search.isPresent());
		assertEquals(planet, search.get());

	}
	
	
	@Test
	public void shouldRemovePlanet() {

		Planet planet = new Planet.Builder().setName("Terra").setWeather("temperate").build();

		planet = repository.save(planet);

		assertThat(planet.getId()).isNotNull();
		assertThat(planet.getName()).isNotNull();
		assertThat(planet.getWeather()).isNotNull();
		
		repository.delete(planet);
		
		Optional<Planet> search = repository.findById(planet.getId());

		assertFalse(search.isPresent());
		
		
	}
	
	@Test
	public void shouldFindByNamePlanet() {

		Planet planet = new Planet.Builder().setName("Terra").setWeather("temperate").build();

		planet = repository.save(planet);
		
		Planet search = repository.findByName(planet.getName());

		assertThat(search.getId()).isNotNull();
		assertThat(search.getName()).isNotNull();
		assertThat(search.getWeather()).isNotNull();
	}



}