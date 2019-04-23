package br.com.luiz.carlos.api.planet.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Planet implements Serializable {

	@Id
	private String id;

	private String name;
	
	private String weather;
	
	private int quantityMovies;
	
	


	public Planet() {
		super();
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public static class Builder {

		public Builder() {
		}


		private String id ;
		
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

		public Planet build() {
			Planet planet = new Planet();
			planet.setName(this.name);
			planet.setId(this.id);
			planet.setWeather(weather);
			planet.setQuantityMovies(this.quantityMovies);
			return planet;
		}
	}

}
