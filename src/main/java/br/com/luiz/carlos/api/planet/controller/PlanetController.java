package br.com.luiz.carlos.api.planet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.luiz.carlos.api.planet.domain.Planet;
import br.com.luiz.carlos.api.planet.dto.PlanetDTO;
import br.com.luiz.carlos.api.planet.exception.PlanetException;
import br.com.luiz.carlos.api.planet.gateway.planet.dto.PlanetSwapiDTO;
import br.com.luiz.carlos.api.planet.service.PlanetService;

@RestController
public class PlanetController {

	@Autowired
	private PlanetService planetService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@RequestBody PlanetDTO planetDTO) {
		Planet planet = new Planet.Builder().setId(planetDTO.getId()).setName(planetDTO.getName())
				.setWeather(planetDTO.getWeather()).build();
		planet=planetService.save(planet);
		return new ResponseEntity<>(createDTOResponse(planet), HttpStatus.OK);

	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll() {
		List<PlanetDTO> listResponse = new ArrayList<>();
		List<Planet> planets = planetService.findAll();
		for (Planet planet : planets) {
			listResponse.add(createDTOResponse(planet));
		}
		return new ResponseEntity<>(listResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/api", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAllApiSwapi() {
		List<PlanetSwapiDTO> planetSwapiDTOs = planetService.findAllSwapi();
		return new ResponseEntity<>(planetSwapiDTOs, HttpStatus.OK);

	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		Planet planet = planetService.findByName(name);
		if(planet!= null){
		return new ResponseEntity<>(createDTOResponse(planet), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(name, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable("id") String id) {
		Planet planet;
		try {
			planet = planetService.find(id);
			return new ResponseEntity<>(createDTOResponse(planet), HttpStatus.OK);

		} catch (PlanetException e) {
			return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> remove(@PathVariable("id") String id) {
		try {
			planetService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (PlanetException e) {
			return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);

		}

	}

	private PlanetDTO createDTOResponse(Planet planet) {
		return new PlanetDTO.Builder().setId(planet.getId()).setName(planet.getName()).setWeather(planet.getWeather())
				.setId(planet.getId()).build();
	}

}
