package com.crud.taskfinal.service;


import com.crud.taskfinal.domain.Task;
import com.crud.taskfinal.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Optional<Task> getTask(Long taskId)  {
        return repository.findById(taskId);
    }

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public void deleteTaskbyId(Long taskId) {
        repository.deleteById(taskId);
    }

}
