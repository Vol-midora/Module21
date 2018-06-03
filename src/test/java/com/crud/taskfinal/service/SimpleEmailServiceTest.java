package com.crud.taskfinal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.*;

import com.crud.taskfinal.domain.Mail;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

	
	@InjectMocks
	private SimpleEmailService simpleEmailService;
	
	@Mock
	private JavaMailSender javaMailSender;
	
	
	@Test
	public void shouldSendEmail() {
		//Given
		Mail mail = new Mail("test@test.com", "Test", "TestMessage");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail.getMailTo());
		mailMessage.setSubject(mail.getSubject());
		mailMessage.setText(mail.getMessage());
		
		//When
		simpleEmailService.send(mail);
		
		//Then
		verify(javaMailSender, times(1)).send(mailMessage);
	}
}
