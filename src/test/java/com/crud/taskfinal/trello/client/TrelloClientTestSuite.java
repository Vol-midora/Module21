package com.crud.taskfinal.trello.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.taskfinal.domain.TrelloBoardDto;
import com.crud.taskfinal.domain.TrelloListDto;
import com.crud.taskfinal.domain.trelloCard.CreatedTrelloCardDto;
import com.crud.taskfinal.domain.trelloCard.TrelloCardDto;
import com.crud.taskfinal.trello.config.TrelloConfig;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTestSuite {
	
	@InjectMocks
	private TrelloClient client;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private TrelloConfig trelloConfig;

	
	@Test
	public void testGetUrl() {
		//Given
		URI expected = UriComponentsBuilder.fromHttpUrl("https://api.trello.com/1" + "/members/" + "volmidora" + "/boards")
                .queryParam("key", "61bb1ac4fb508b96c57dcdb0384369ef")
                .queryParam("token", "87a73e8a3fa848190e56ee13c6c22e9996f2a2bf7efa26314c4e33b3f19ffe8b")
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();

		when(trelloConfig.getTrelloApiEndpoint()).thenReturn("https://api.trello.com/1");
		when(trelloConfig.getTrelloAppKey()).thenReturn("61bb1ac4fb508b96c57dcdb0384369ef");
		when(trelloConfig.getTrelloToken()).thenReturn("87a73e8a3fa848190e56ee13c6c22e9996f2a2bf7efa26314c4e33b3f19ffe8b");
		when(trelloConfig.getTrelloUsername()).thenReturn("volmidora");
		
		//When
		URI actual = client.getUrl();
		
		//Then
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetTrelloBoards() {
		//Given
		URI expected = UriComponentsBuilder.fromHttpUrl("https://api.trello.com/1" + "/members/" + "volmidora" + "/boards")
                .queryParam("key", "61bb1ac4fb508b96c57dcdb0384369ef")
                .queryParam("token", "87a73e8a3fa848190e56ee13c6c22e9996f2a2bf7efa26314c4e33b3f19ffe8b")
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();
		
		List<TrelloListDto> trelloLists = new ArrayList<>();
		trelloLists.add(new TrelloListDto("1", "test_list", false));
		List<TrelloBoardDto> trelloBoards = new ArrayList<>();
		TrelloBoardDto board = new TrelloBoardDto("1", "test", trelloLists);
		trelloBoards.add(board);
		TrelloBoardDto[] boardArray = {board};
		
		when(restTemplate.getForObject(expected, TrelloBoardDto[].class)).thenReturn(boardArray);
		when(trelloConfig.getTrelloApiEndpoint()).thenReturn("https://api.trello.com/1");
		when(trelloConfig.getTrelloAppKey()).thenReturn("61bb1ac4fb508b96c57dcdb0384369ef");
		when(trelloConfig.getTrelloToken()).thenReturn("87a73e8a3fa848190e56ee13c6c22e9996f2a2bf7efa26314c4e33b3f19ffe8b");
		when(trelloConfig.getTrelloUsername()).thenReturn("volmidora");
		
		//When
		List<TrelloBoardDto> resultList = client.getTrelloBoards();
		
		//Then
		Assert.assertNotNull(resultList);
		resultList.forEach(boardDto -> {
			Assert.assertEquals(boardDto.getId(), board.getId());
			Assert.assertEquals(boardDto.getName(), board.getName());
			Assert.assertEquals(boardDto.getLists().size(), board.getLists().size());
		});	
	}
	
	@Test
	public void testCreateNewCard() {
		//Given
		TrelloCardDto cardDto = new TrelloCardDto("1", "test", "testPos", "testcostam");
		CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "test", "testPos");
		
		when(restTemplate.postForObject(any(), any(), any())).thenReturn(createdCard);
		when(trelloConfig.getTrelloApiEndpoint()).thenReturn("https://api.trello.com/1");
		when(trelloConfig.getTrelloAppKey()).thenReturn("61bb1ac4fb508b96c57dcdb0384369ef");
		when(trelloConfig.getTrelloToken()).thenReturn("87a73e8a3fa848190e56ee13c6c22e9996f2a2bf7efa26314c4e33b3f19ffe8b");
		when(trelloConfig.getTrelloUsername()).thenReturn("volmidora");
		
		//When
		CreatedTrelloCardDto resultCard = client.createNewCard(cardDto);
		
		//Then
		Assert.assertEquals(cardDto.getName(), resultCard.getName());
	}

}
