package com.crud.taskfinal.trello.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crud.taskfinal.domain.*;
import com.crud.taskfinal.domain.trelloCard.CreatedTrelloCardDto;
import com.crud.taskfinal.domain.trelloCard.TrelloCardDto;
import com.crud.taskfinal.mapper.TrelloMapper;
import com.crud.taskfinal.service.TrelloService;
import com.crud.taskfinal.trello.validator.TrelloValidator;

@Component
public class TrelloFacade {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

	@Autowired
	private TrelloService trelloService;
	
	@Autowired
	private TrelloMapper mapper;
	
	@Autowired
	private TrelloValidator validator;
	
	public List<TrelloBoardDto> fetchTrelloBoards() {
		List<TrelloBoard> boardList = mapper.maptoBoards(trelloService.fetchTrelloBoards());
		return mapper.mapToBoardDto(validator.validateTrelloBoards(boardList));
	}
	
	public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
		
		TrelloCard trelloCard = mapper.mapToTrelloCard(trelloCardDto);
		validator.validateCard(trelloCard);
		return trelloService.createTrelloCard(mapper.mapToTrelloCardDto(trelloCard));
	}
}
