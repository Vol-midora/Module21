package com.crud.taskfinal.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crud.taskfinal.config.AdminConfig;
import com.crud.taskfinal.domain.Mail;
import com.crud.taskfinal.repository.TaskRepository;
import com.crud.taskfinal.service.SimpleEmailService;

@Component
public class EmailScheduler {
	private final String SUBJECT = "This is information e-mail";
	
	@Autowired
	private SimpleEmailService simpleEmailService;
	
	@Autowired
	private TaskRepository repository;
	
	@Autowired
	private AdminConfig adminConfig;
	
	@Scheduled(cron = "0 */5 13 * * *")
	public void sendInformationEmail() {
		long size = repository.count();
		String task = size==1?"task":"tasks";
		simpleEmailService.send(new Mail(adminConfig.getEmail(), SUBJECT, "You've got " + size + " " + task + " in your database"));
	}

}
