package com.crud.taskfinal.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.crud.taskfinal.domain.TrelloBoardDto;
import com.crud.taskfinal.domain.TrelloListDto;
import com.crud.taskfinal.domain.trelloCard.CreatedTrelloCardDto;
import com.crud.taskfinal.domain.trelloCard.TrelloCardDto;
import com.crud.taskfinal.trello.facade.TrelloFacade;
import com.google.gson.Gson;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TrelloFacade trelloFacade;
	
	@Test
	public void shouldFetchEmptyTrelloBoards() throws Exception {
		//Given
		List<TrelloBoardDto> trelloBoards = new ArrayList<>();
		when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
		//When&Then
		mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is(200)) //or isOk
			.andExpect(jsonPath("$", hasSize(0)));		
	}
	
	@Test
	public void shouldFetchTrelloBoards() throws Exception {
		//Given
		List<TrelloListDto> trelloLists = new ArrayList<>();
		trelloLists.add(new TrelloListDto("Test List", "Test List", false));
		
		List<TrelloBoardDto> trelloBoards = new ArrayList<>();
		trelloBoards.add(new TrelloBoardDto("test", "test", trelloLists));
		when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
		
		
		//When & Then
		mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		//TrelloBoard fields
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].id", is("test")))
		.andExpect(jsonPath("$[0].name", is("test")))
		//TrelloListfields
		.andExpect(jsonPath("$[0].lists", hasSize(1)))
		.andExpect(jsonPath("$[0].lists[0].id", is("Test List")))
		.andExpect(jsonPath("$[0].lists[0].name", is("Test List")))
		.andExpect(jsonPath("$[0].lists[0].closed", is(false)));	
	}
	
	@Test
	public void shouldCreateTrelloCard() throws Exception {
		//Given
		TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "top", "1");
		
		CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("name", "1", "testUrl");
		
		when(trelloFacade.createCard(ArgumentMatchers.any())).thenReturn(createdTrelloCardDto);
		
		Gson gson = new Gson();
		String jsonContent = gson.toJson(trelloCardDto);
		
		//When&Then
		mockMvc.perform(post("/v1/trello/createTrelloCard")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(jsonContent))
				.andExpect(jsonPath("$.id", is("1")))
				.andExpect(jsonPath("$.name", is("name")))
				.andExpect(jsonPath("$.shortUrl", is("testUrl")));
	}
}
