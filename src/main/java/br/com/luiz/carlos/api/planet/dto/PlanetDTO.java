package br.com.luiz.carlos.api.planet.dto;

import java.util.Arrays;

import br.com.luiz.carlos.api.planet.domain.Planet;
import br.com.luiz.carlos.api.planet.domain.Planet.Builder;

public class PlanetDTO {

	private String id;

	private String name;

	private String weather;

	private int quantityMovies;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public int getQuantityMovies() {
		return quantityMovies;
	}

	public void setQuantityMovies(int quantityMovies) {
		this.quantityMovies = quantityMovies;
	}

	public static class Builder {

		public Builder() {
		}

		private String id;

		private String name;

		private String weather;

		private int quantityMovies;;

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setWeather(String weather) {
			this.weather = weather;
			return this;
		}

		public PlanetDTO build() {
			PlanetDTO planetDTO = new PlanetDTO();
			planetDTO.setName(this.name);
			planetDTO.setId(this.id);
			planetDTO.setWeather(weather);
			planetDTO.setQuantityMovies(this.quantityMovies);
			return planetDTO;
		}
	}

}
