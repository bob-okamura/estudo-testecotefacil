package br.com.bobhome.tests;

import br.com.bobhome.dtos.PlanetDTO;
import br.com.bobhome.entities.Planet;

public class Factory {
	
	public static Planet createPlanet() {
		Planet planet = new Planet(1L, "Marte", "Temperate", "Montains");
		return planet;
	}
	
	public static PlanetDTO createPlanetDTO() {
		Planet planet = createPlanet();
		return new PlanetDTO(planet);
	}
	
}
