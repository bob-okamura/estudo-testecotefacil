package br.com.bobhome.entities;

import java.util.List;

public class ResponsePlanetTO {
	
	private List<PlanetTO> results;
	
	public List<PlanetTO> getResults() {
		return results;
	}
	public void setResults(List<PlanetTO> results) {
		this.results = results;
	}
	
}
