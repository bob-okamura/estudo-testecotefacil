package br.com.bobhome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import br.com.bobhome.dtos.PlanetDTO;
import br.com.bobhome.repositories.PlanetRepositoty;
import br.com.bobhome.services.exceptions.ResourceNotFoundException;
import br.com.bobhome.tests.Factory;

@SpringBootTest
@Transactional
public class PlanetServiceIT { // IT Integration Test
	
	@Autowired
	private PlanetService service;
	
	@Autowired
	private PlanetRepositoty repository;
	
	private Long countTotalPlanets;
	private Long existingId;
	private Long nonExistingId;
	private PlanetDTO planetDTO;
		
	@BeforeEach
	void setUp() throws Exception {
		countTotalPlanets = 5L;
		existingId = 1L;
		nonExistingId = 1000L;
		planetDTO = Factory.createPlanetDTO();
	}
	
	@Test
	public void insertShouldInsertData() {
		service.insert(planetDTO);
		
		Assertions.assertTrue(true);
	}

	@Test
	public void findAllPagedShouldReturnPageWhenPage0Size10() {
		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<PlanetDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(10, result.getSize());
		Assertions.assertEquals(countTotalPlanets, result.getTotalElements());
	}
	
	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName() {
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));

		Page<PlanetDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("Alderaan", result.getContent().get(0).getName());
		Assertions.assertEquals("Bespin", result.getContent().get(1).getName());
		Assertions.assertEquals("Dagobah", result.getContent().get(2).getName());
	}
	
	@Test
	public void findByIdShouldReturnPlanetWhenIdExists() {

		PlanetDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(existingId, result.getId());
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	@Test
	public void deleteShouldDeleteWhenIdExists() {
		
		service.delete(existingId);
		
		Assertions.assertEquals(countTotalPlanets - 1, repository.count());
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}

}
