package com.crud.taskfinal.facade;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.crud.taskfinal.domain.TrelloBoard;
import com.crud.taskfinal.domain.TrelloBoardDto;
import com.crud.taskfinal.domain.TrelloList;
import com.crud.taskfinal.domain.TrelloListDto;
import com.crud.taskfinal.mapper.TrelloMapper;
import com.crud.taskfinal.service.TrelloService;
import com.crud.taskfinal.trello.facade.TrelloFacade;
import com.crud.taskfinal.trello.validator.TrelloValidator;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

	@InjectMocks
	private TrelloFacade trelloFacade;
	
	@Mock
	private TrelloService trelloService;
	
	@Mock
	private TrelloValidator trelloValidator;
	
	@Mock
	private TrelloMapper trelloMapper;
	
	@Test
	public void shouldFetchEmptyList() {
		//Given
		List<TrelloListDto> trelloLists = new ArrayList<>();
		trelloLists.add(new TrelloListDto("1", "test_list", false));
		List<TrelloBoardDto> trelloBoards = new ArrayList<>();
		trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));
		
		List<TrelloList> mappedTrelloList = new ArrayList<>();
		mappedTrelloList.add(new TrelloList("1", "test_list", false));
		
		List<TrelloBoard> mappedBoardList = new ArrayList<>();
		mappedBoardList.add(new TrelloBoard("1", "test", mappedTrelloList));
		
		when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
		when(trelloMapper.maptoBoards(trelloBoards)).thenReturn(mappedBoardList);
		when(trelloMapper.mapToBoardDto(anyList())).thenReturn(new ArrayList<>());
		when(trelloValidator.validateTrelloBoards(mappedBoardList)).thenReturn(new ArrayList<>());
		
		//When
		List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
		
		//Then
		Assert.assertNotNull(trelloBoardDtos);
		Assert.assertEquals(0, trelloBoardDtos.size());
	}
	
	@Test
	public void shouldFetchBoards() {
		//Given
		List<TrelloListDto> trelloLists = new ArrayList<>();
		trelloLists.add(new TrelloListDto("1", "test_list", false));
		List<TrelloBoardDto> trelloBoards = new ArrayList<>();
		trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));
		
		List<TrelloList> mappedTrelloList = new ArrayList<>();
		mappedTrelloList.add(new TrelloList("1", "test_list", false));
		
		List<TrelloBoard> mappedBoardList = new ArrayList<>();
		mappedBoardList.add(new TrelloBoard("1", "test", mappedTrelloList));
		
		when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
		when(trelloMapper.maptoBoards(trelloBoards)).thenReturn(mappedBoardList);
		when(trelloMapper.mapToBoardDto(anyList())).thenReturn(trelloBoards);
		when(trelloValidator.validateTrelloBoards(mappedBoardList)).thenReturn(mappedBoardList);
		
		//When
		List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
		
		//Then
		Assert.assertNotNull(trelloBoardDtos);
		Assert.assertEquals(1, trelloBoardDtos.size());
		
		trelloBoardDtos.forEach(trelloBoardDto -> {
			Assert.assertEquals("1", trelloBoardDto.getName());
			Assert.assertEquals("test", trelloBoardDto.getId());
			trelloBoardDto.getLists().forEach(ListDto -> {
				Assert.assertEquals("1", ListDto.getName());
				Assert.assertEquals("test_list", ListDto.getId());
				Assert.assertEquals(false, ListDto.isClosed());
				});
			});
		
	}
}
