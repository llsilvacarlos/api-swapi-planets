package br.com.luiz.carlos.api.planet.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.luiz.carlos.api.planet.domain.Planet;
import br.com.luiz.carlos.api.planet.exception.PlanetException;
import br.com.luiz.carlos.api.planet.gateway.planet.PlanetSwapiGateway;
import br.com.luiz.carlos.api.planet.gateway.planet.dto.PlanetSwapiDTO;
import br.com.luiz.carlos.api.planet.repository.PlanetRepository;
import br.com.luiz.carlos.api.planet.service.impl.PlanetServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class PlanetServiceTests {

	@Configuration
	static class Config {

		@Bean
		public PlanetService planetService() {
			PlanetService planetService = new PlanetServiceImpl();
			return planetService;
		}
	}

	@MockBean
	private PlanetRepository repository;

	@MockBean
	private PlanetSwapiGateway planetSwapiGateway;

	@Autowired
	private PlanetService planetService;

	@Test
	public void shouldSaveNewPlanetSucess() {

		Planet planet = new Planet.Builder().setName("Terra").setWeather("Quente").build();

		when(repository.save(any())).then(new Answer<Planet>() {

			@Override
			public Planet answer(InvocationOnMock ioMock) throws Throwable {
				Planet planet = (Planet) ioMock.getArguments()[0];
				Random random = new Random();
				int val = random.nextInt();
				String Hex = new String();
				Hex = Integer.toHexString(val);

				planet.setId(Hex);
				return planet;
			}

		});

		when(planetSwapiGateway.findByName(any())).thenReturn(null);

		planetService.save(planet);
		assertNotNull(planet.getId());
		assertThat(planet.getQuantityMovies(), is(0));

	}

	@Test
	public void shouldSavePlanetAlreadPlanetApiSucess() {

		Planet planet = new Planet.Builder().setName("Alderaan").setWeather("Quente").build();

		when(repository.save(any())).then(new Answer<Planet>() {

			@Override
			public Planet answer(InvocationOnMock ioMock) throws Throwable {
				Planet planet = (Planet) ioMock.getArguments()[0];
				Random random = new Random();
				int val = random.nextInt();
				String Hex = new String();
				Hex = Integer.toHexString(val);

				planet.setId(Hex);
				return planet;
			}

		});

		when(planetSwapiGateway.findByName(any()))
				.thenReturn(new PlanetSwapiDTO.Builder().setName("Alderaan").setQuantity(4).build());

		planetService.save(planet);
		assertNotNull(planet.getId());
		assertThat(planet.getQuantityMovies(), is(4));

	}

	@Test
	public void shouldFindByNameFound() {

		when(repository.findByName(any()))
				.thenReturn(new Planet.Builder().setId(Integer.toHexString(new Random().nextInt())).setName("Alderaan")
						.setWeather("temperate").build());

		Planet planet = planetService.findByName("Alderaan");
		assertNotNull(planet.getId());

	}

	@Test
	public void shouldFindByNameNotFound() {

		when(repository.findByName(any())).thenReturn(null);

		Planet planet = planetService.findByName("Terra");
		assertNull(planet);

	}

	@Test
	public void shouldFindByFound() throws PlanetException {

		when(repository.findById(any()))
				.thenReturn(Optional.of(new Planet.Builder().setId(Integer.toHexString(new Random().nextInt()))
						.setName("Alderaan").setWeather("temperate").build()));

		Planet planet = planetService.find("1");
		assertNotNull(planet.getId());

	}

	@Test(expected = PlanetException.class)
	public void shouldFindByNotFound() throws PlanetException {

		when(repository.findById(any())).thenReturn(Optional.empty());

		planetService.find("2");

	}

	@Test
	public void shouldFindAll() throws PlanetException {

		List<Planet> ListReturn = new ArrayList<>();
		ListReturn.add(new Planet.Builder().setId(Integer.toHexString(new Random().nextInt())).setName("Alderaan")
				.setWeather("temperate").build());

		when(repository.findAll()).thenReturn(ListReturn);

		List<Planet> listSearch = planetService.findAll();
		assertNotNull(listSearch);

	}

	@Test
	public void shouldFindAllEmpty() throws PlanetException {

		when(repository.findAll()).thenReturn(new ArrayList<>());

		List<Planet> listSearch = planetService.findAll();
		assertNotNull(listSearch);
		assertTrue(listSearch.isEmpty());

	}
	
	@Test
	public void shouldFindAllSwapi() throws PlanetException {

		List<PlanetSwapiDTO> ListReturn = new ArrayList<>();
		ListReturn.add(new PlanetSwapiDTO());

		when(planetSwapiGateway.findAll()).thenReturn(ListReturn);

		List<Planet> listSearch = planetService.findAll();
		assertNotNull(listSearch);

	}

	@Test
	public void shouldFindAllSwapiEmpty() throws PlanetException {

		when(planetSwapiGateway.findAll()).thenReturn(new ArrayList<>());

		List<Planet> listSearch = planetService.findAll();
		assertNotNull(listSearch);
		assertTrue(listSearch.isEmpty());

	}

	public void shoudRemoveThereObject() throws PlanetException {
		when(repository.findById(any()))
				.thenReturn(Optional.of(new Planet.Builder().setId(Integer.toHexString(new Random().nextInt()))
						.setName("Alderaan").setWeather("temperate").build()));
		verify(repository, times(0)).delete(any());

		planetService.remove("1");

	}

	@Test(expected = PlanetException.class)
	public void shoudRemoveNoThereObject() throws PlanetException {

		verify(repository, times(0)).delete(any());

		planetService.remove("1");

	}

}
