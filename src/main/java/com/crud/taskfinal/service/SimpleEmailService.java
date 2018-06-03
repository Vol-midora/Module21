package com.crud.taskfinal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.crud.taskfinal.domain.Mail;

@Service
public class SimpleEmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);
	
	public void send(final Mail mail) {
		LOGGER.info("Starting mail preparation");
		try {
			SimpleMailMessage mailMessage = createMail(mail);
			LOGGER.info("Mail message created successfully");
			javaMailSender.send(mailMessage);
			LOGGER.info("Mail sent to:" + mail.getMailTo());
		} catch (MailException e) {
		LOGGER.error("Error sending email: " + e.getMessage()+e);
		}
	}
	
	private SimpleMailMessage createMail(final Mail mail) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail.getMailTo());
		mailMessage.setSubject(mail.getSubject());
		mailMessage.setText(mail.getMessage());
		if (mail.getCc() != null) {
			mailMessage.setCc(mail.getCc());
		}
		return mailMessage;
	}
	
}
