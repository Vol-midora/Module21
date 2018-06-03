package com.crud.taskfinal.repository;


import com.crud.taskfinal.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    //@Override
    Optional<Task> findById(Long taskId);

    @Override
    Task save(Task task);

    //@Override
    void deleteById(Long taskId);
    
    @Override
    long count();
}

