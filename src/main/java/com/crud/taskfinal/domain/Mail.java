package com.crud.taskfinal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Mail {
	private String mailTo;
	private String subject;
	private String message;
	private String cc = null;
	
	public Mail(final String mailTo, final String subject, final String message) {
		this.mailTo = mailTo;
		this.subject = subject;
		this.message = message;
	}

}
