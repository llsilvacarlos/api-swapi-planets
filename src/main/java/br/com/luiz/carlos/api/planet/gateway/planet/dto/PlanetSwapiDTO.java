package br.com.luiz.carlos.api.planet.gateway.planet.dto;

public class PlanetSwapiDTO {
	
	private Long id;
	
	private String name;
	
	private int quantity;
	
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		PlanetSwapiDTO other = (PlanetSwapiDTO) obj;
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


		private Long id ;
		
		private String name;
		
		private int  quantity;
		

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public PlanetSwapiDTO build() {
			PlanetSwapiDTO planetSwapiDTO = new PlanetSwapiDTO();
			planetSwapiDTO.setName(this.name);
			planetSwapiDTO.setId(this.id);
			planetSwapiDTO.setQuantity(this.quantity);
			return planetSwapiDTO;
		}
	}
	
	
	
	

}
