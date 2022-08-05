package br.com.bobhome.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bobhome.dtos.PlanetDTO;
import br.com.bobhome.tests.Factory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PlanetControllerIT { //IT Integration Test
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper; 
	
	private PlanetDTO planetDTO;
	private Long countTotalPlanets;
	
	@BeforeEach
	void setUp() throws Exception {
		planetDTO = Factory.createPlanetDTO();
		countTotalPlanets = 5L;
	}
	
	@Test
	public void insertShouldReturnProductDTOCreated() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(planetDTO);
		
		mockMvc.perform(post("/planets")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		;
	}
	
	@Test
	public void findAllShouldReturnAllResourcesPaged() throws Exception {
		mockMvc.perform(get("/planets")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalElements").value(countTotalPlanets))
		.andExpect(jsonPath("$.content").exists())
		.andExpect(jsonPath("$.content[0].name").value("Alderaan"))
		.andExpect(jsonPath("$.content[1].name").value("Hoth"))
		.andExpect(jsonPath("$.content[2].name").value("Dagobah"));
	}
	
}
