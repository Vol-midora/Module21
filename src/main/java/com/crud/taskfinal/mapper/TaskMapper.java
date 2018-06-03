package com.crud.taskfinal.mapper;


import com.crud.taskfinal.domain.Task;
import com.crud.taskfinal.domain.TaskDto;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    public Task mapToTask(TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getContent()
        );
    }

    public TaskDto mapToTaskDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getContent());
    }

    public List<TaskDto> mapToTaskDtoList (final List<Task> taskList) {
        return taskList.stream()
                .map(n->new TaskDto(n.getId(),n.getTitle(),n.getContent()))
                .collect(Collectors.toList());
    }
}
