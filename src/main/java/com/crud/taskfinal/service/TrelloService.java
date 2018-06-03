package com.crud.taskfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.taskfinal.config.AdminConfig;
import com.crud.taskfinal.domain.Mail;
import com.crud.taskfinal.domain.TrelloBoardDto;
import com.crud.taskfinal.domain.trelloCard.CreatedTrelloCardDto;
import com.crud.taskfinal.domain.trelloCard.TrelloCardDto;
import com.crud.taskfinal.trello.client.TrelloClient;
import java.util.Optional;

@Service
public class TrelloService {
	
	private final static String SUBJECT = "Tasks: New Trello Card";

	@Autowired
	private TrelloClient trelloClient;
	
	@Autowired
	private SimpleEmailService mailService;
	
	@Autowired
	private AdminConfig adminConfig;
	
	
	public List<TrelloBoardDto> fetchTrelloBoards() {
		return trelloClient.getTrelloBoards();
	}
	
	public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto taskDto) {
		CreatedTrelloCardDto newCard = trelloClient.createNewCard(taskDto);
		Optional.ofNullable(newCard).ifPresent(card -> mailService.send(new Mail(
				adminConfig.getEmail(),
				SUBJECT,
				"New card: " + card.getName() + " has been created on your Trello account")));
		return newCard;
	}
	
}
