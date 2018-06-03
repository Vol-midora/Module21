package com.crud.taskfinal.domain.trelloCard;

import org.junit.Test;

import org.junit.Assert;

public class TrelloCardPackageTestSuite {
	
	@Test
	public void testTrelloCardPackageClasses() {
		CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("testName", "testId", "testShortUrl");
		Trello trello = new Trello(1, 2);
		AttachmentsByType attachments = new AttachmentsByType(trello);
		
		//when
		TrelloCardBadges badges = new TrelloCardBadges(1, attachments);
		
		//Then
		Assert.assertEquals(1, badges.getAttachments().getTrello().getBoard());
		Assert.assertEquals(2, badges.getAttachments().getTrello().getCard());
		Assert.assertEquals(1, badges.getVotes());	
	}

}
