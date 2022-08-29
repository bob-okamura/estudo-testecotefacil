package br.com.bobhome.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bobhome.dtos.PlanetDTO;
import br.com.bobhome.entities.PlanetTO;
import br.com.bobhome.services.PlanetService;
import br.com.bobhome.services.SwapiPlanetService;

@RestController
@RequestMapping("/planets")
public class PlanetController {
	
	@Autowired
	private PlanetService service;
	
	@Autowired
	private SwapiPlanetService swapiPlanetService;
	
	@GetMapping("/swapi/counting")
	public ResponseEntity<Integer> countingApparitions(@PathVariable
			@RequestParam(name="name", value = "")String name){
		int planetTO = swapiPlanetService.findQuantityOfApparitionsInMovies(name);
		return ResponseEntity.ok().body(planetTO);
	}
	
	@GetMapping("/swapi/names")
	public ResponseEntity<PlanetTO> findPlanetsApi(@PathVariable
			@RequestParam(name="name", value = "")String name){
		PlanetTO planetTO = swapiPlanetService.findPlanetByName(name);
		return ResponseEntity.ok().body(planetTO);
	}
	
	@PostMapping
	public ResponseEntity<PlanetDTO> insert(@RequestBody PlanetDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<PlanetDTO>> findAll(Pageable pageable){
		Page<PlanetDTO> page = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping("/names")
	public ResponseEntity<List<PlanetDTO>> findByName(@PathVariable
			@RequestParam(name="name", value = "")String name){
		List<PlanetDTO> list = service.findByName(name);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlanetDTO> findById(@PathVariable Long id){
		PlanetDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
