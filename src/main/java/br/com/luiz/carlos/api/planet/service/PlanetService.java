package br.com.luiz.carlos.api.planet.service;

import java.util.List;

import br.com.luiz.carlos.api.planet.domain.Planet;
import br.com.luiz.carlos.api.planet.domain.Planet;
import br.com.luiz.carlos.api.planet.exception.PlanetException;
import br.com.luiz.carlos.api.planet.gateway.planet.dto.PlanetSwapiDTO;

public interface PlanetService {

	public Planet save(Planet planet) ;
	
	public Planet findByName(String name ) ;

	public Planet find(String id) throws PlanetException ;
	
	public List<Planet> findAll ();

	public void remove(String id) throws PlanetException;
	
	public List<PlanetSwapiDTO> findAllSwapi();
	
	
}
