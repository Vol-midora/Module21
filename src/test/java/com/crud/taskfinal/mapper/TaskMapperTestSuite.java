package com.crud.taskfinal.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.crud.taskfinal.domain.Task;
import com.crud.taskfinal.domain.TaskDto;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
	
	@InjectMocks
	private TaskMapper mapper;
	
	@Test
	public void testMapToTaskDto() {
		//Given
		Task task = new Task((long) 1, "test name", "test description");
		
		//When
		TaskDto result = mapper.mapToTaskDto(task);
		
		//Then
		Assert.assertEquals((Long) (long) 1, result.getId());
		Assert.assertEquals("test name",  result.getTitle());
		Assert.assertEquals("test description", result.getContent());
	}
	
	@Test
	public void testMapToTask() {
		//Given
		TaskDto taskDto =  new TaskDto((long) 1, "test name", "test description");
		
		//When
		Task result = mapper.mapToTask(taskDto);
		
		//Then
		Assert.assertEquals((Long) (long) 1, result.getId());
		Assert.assertEquals("test name",  result.getTitle());
		Assert.assertEquals("test description", result.getContent());
	}

}
