package com.crud.taskfinal.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud.taskfinal.config.AdminConfig;
import com.crud.taskfinal.domain.TrelloBoardDto;
import com.crud.taskfinal.domain.TrelloListDto;
import com.crud.taskfinal.domain.trelloCard.CreatedTrelloCardDto;
import com.crud.taskfinal.domain.trelloCard.TrelloCardDto;
import com.crud.taskfinal.trello.client.TrelloClient;

import org.junit.Assert;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

	@InjectMocks
	private TrelloService service;
	
	@Mock
	private TrelloClient trelloClient;
	
	@Mock
	private SimpleEmailService mailService;
	
	@Mock
	private AdminConfig adminConfig;
	
	
	@Test
	public void testFetchTrelloBoards() {
		//Given
		List<TrelloListDto> trelloLists = new ArrayList<>();
		trelloLists.add(new TrelloListDto("1", "test_list", false));
		List<TrelloBoardDto> trelloBoards = new ArrayList<>();
		TrelloBoardDto board = new TrelloBoardDto("1", "test", trelloLists);
		trelloBoards.add(board);
		
		when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
		
		//when
		List<TrelloBoardDto> result = service.fetchTrelloBoards();
		
		//Then
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(board.getName(), result.get(0).getName());
	}
	
	@Test
	public void testCreateCard() {
		//Given
				TrelloCardDto cardDto = new TrelloCardDto("1", "test", "testPos", "testcostam");
				CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "test", "testPos");
				
		when(trelloClient.createNewCard(cardDto)).thenReturn(createdCard);
		
		//When
		CreatedTrelloCardDto resultCard = service.createTrelloCard(cardDto);
		
		//Then
		Assert.assertEquals(cardDto.getName(), resultCard.getName());
	}
}
