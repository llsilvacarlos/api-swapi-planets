package br.com.luiz.carlos.api.planet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luiz.carlos.api.planet.domain.Planet;
import br.com.luiz.carlos.api.planet.domain.Planet;
import br.com.luiz.carlos.api.planet.exception.PlanetException;
import br.com.luiz.carlos.api.planet.gateway.planet.PlanetSwapiGateway;
import br.com.luiz.carlos.api.planet.gateway.planet.dto.PlanetSwapiDTO;
import br.com.luiz.carlos.api.planet.repository.PlanetRepository;
import br.com.luiz.carlos.api.planet.service.PlanetService;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	private PlanetRepository repository;
	
	@Autowired
	private PlanetSwapiGateway planetSwapiGateway;
	
	
	@Override
	public Planet save(Planet planet) {
		
		PlanetSwapiDTO planetSwapiDTO = planetSwapiGateway.findByName(planet.getName());
		
		Planet planetSearch = this.findByName(planet.getName());
		
		if(planetSearch != null){ //mantem a indepotencia do servico
			planetSearch.setQuantityMovies(planet.getQuantityMovies());
			planetSearch.setWeather(planet.getWeather());
			
			repository.save(planetSearch);
			return planetSearch;
		}
		
		if(planetSwapiDTO != null){
			planet.setQuantityMovies(planetSwapiDTO.getQuantity());
		}
		
		repository.save(planet);

		return planet;
	}
	
	public List<PlanetSwapiDTO> findAllSwapi(){
		return planetSwapiGateway.findAll();
	}

	@Override
	public Planet findByName(String name) {
		
		return repository.findByName(name);
	}

	@Override
	public Planet find(String id) throws PlanetException {
		 Optional<Planet> search = repository.findById(id);
		 if(search.isPresent()){
			 return search.get();
		 }else{
			 throw new PlanetException("Planeta nao encontrado na base de dados");
		 }
	}

	@Override
	public List<Planet> findAll() {
		 return repository.findAll();
	}

	@Override
	public void remove(String id) throws PlanetException {
		 repository.delete(this.find(id));
		
	}

	
	
	
}
