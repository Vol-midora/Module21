package com.crud.taskfinal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class AdminConfig {
	
@Value("${admin.mail}")
private String email;
}
