package com.crud.taskfinal.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.crud.taskfinal.domain.*;
import com.crud.taskfinal.domain.trelloCard.TrelloCardDto;

@Component
public class TrelloMapper {
	
	public List<TrelloBoard> maptoBoards(List<TrelloBoardDto> boardDto) {
		return boardDto.stream()
				.map(trelloBoard -> new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists())))
				.collect(Collectors.toList());
	}
	
	public List<TrelloList> mapToList(final List<TrelloListDto> listDto) {
		return listDto.stream()
				.map(list -> new TrelloList(list.getId(), list.getName(), list.isClosed()))
				.collect(Collectors.toList());
	}
	
	public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloList) {
		return trelloList.stream()
				.map(list -> new TrelloListDto(list.getId(), list.getName(), list.isClosed()))
				.collect(Collectors.toList());
	}
	
	public List<TrelloBoardDto> mapToBoardDto (List<TrelloBoard> boardList) {
		return boardList.stream()
				.map(board-> new TrelloBoardDto(board.getId(), board.getName(), mapToListDto(board.getLists())))
				.collect(Collectors.toList());
	}
	
	public TrelloCardDto mapToTrelloCardDto(TrelloCard trelloCard) {
		return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
	}
	
	public TrelloCard mapToTrelloCard(TrelloCardDto trelloCard) {
		return new TrelloCard(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getIdList());
	}

}
