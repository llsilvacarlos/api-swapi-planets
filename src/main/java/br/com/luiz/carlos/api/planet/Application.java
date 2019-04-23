package br.com.luiz.carlos.api.planet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.luiz.carlos.api.planet.gateway.planet.PlanetSwapiGateway;
import br.com.luiz.carlos.api.planet.gateway.planet.impl.PlanetSwapiGatewayImpl;

@SpringBootApplication
@EnableCaching
public class Application {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);


	@Autowired PlanetSwapiGateway planetSwapiGateway;

	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	 @Bean
	   public PlanetSwapiGateway planetSwapiGateway() {
		 PlanetSwapiGateway planetSwapiGateway =new PlanetSwapiGatewayImpl();  
		 LOG.info("Carrecango o cache da API swapi.co");
		 planetSwapiGateway.findAll();
		 return planetSwapiGateway;
	   }

}
