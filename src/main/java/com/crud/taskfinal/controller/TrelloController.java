package com.crud.taskfinal.controller;

import com.crud.taskfinal.domain.TrelloBoardDto;
import com.crud.taskfinal.domain.trelloCard.CreatedTrelloCardDto;
import com.crud.taskfinal.domain.trelloCard.TrelloCardDto;
import com.crud.taskfinal.service.TrelloService;
import com.crud.taskfinal.trello.client.TrelloClient;
import com.crud.taskfinal.trello.facade.TrelloFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloFacade facade;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return facade.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloTaskDto) {
        return facade.createCard(trelloTaskDto);
    }

}
