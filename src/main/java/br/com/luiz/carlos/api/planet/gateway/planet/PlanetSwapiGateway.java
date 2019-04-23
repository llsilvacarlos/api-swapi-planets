package br.com.luiz.carlos.api.planet.gateway.planet;

import java.util.List;

import br.com.luiz.carlos.api.planet.gateway.planet.dto.PlanetSwapiDTO;

public interface PlanetSwapiGateway {

	public List<PlanetSwapiDTO> findAll( );
	
	public PlanetSwapiDTO findByName(String name);
}
