package br.com.bobhome.dtos;

import java.io.Serializable;

import br.com.bobhome.entities.Planet;

public class PlanetDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String climate;
	private String terrain;
	
	public PlanetDTO() {
	}
	
	public PlanetDTO(Long id, String name, String climate, String terrain) {
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	public PlanetDTO(Planet entity) {
		id = entity.getId();
		name = entity.getName();
		climate = entity.getClimate();
		terrain = entity.getTerrain();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
}
