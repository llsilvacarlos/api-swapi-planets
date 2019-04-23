package br.com.luiz.carlos.api.planet.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.luiz.carlos.api.planet.domain.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {
	
    public Planet findByName(String name);

	
   

}
