package br.com.bobhome.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bobhome.dtos.PlanetDTO;
import br.com.bobhome.entities.Planet;
import br.com.bobhome.repositories.PlanetRepositoty;
import br.com.bobhome.services.exceptions.ResourceNotFoundException;

@Service
public class PlanetService {

	@Autowired
	private PlanetRepositoty repository;

	@Transactional
	public PlanetDTO insert(PlanetDTO dto) {
		Planet entity = new Planet();
		entity.setName(dto.getName());
		entity.setClimate(dto.getClimate());
		entity.setTerrain(dto.getTerrain());
		entity = repository.save(entity);
		return new PlanetDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<PlanetDTO> findAllPaged(Pageable pageable) {
		Page<Planet> page = repository.findAll(pageable);
		return page.map(x -> new PlanetDTO(x));
	}

	@Transactional(readOnly = true)
	public List<PlanetDTO> findByName(String name) {
		List<Planet> list = repository.findByName(name);
		return list.stream().map(x -> new PlanetDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public PlanetDTO findById(Long id) {
		Optional<Planet> obj = repository.findById(id);
		Planet entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new PlanetDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}
		
}
