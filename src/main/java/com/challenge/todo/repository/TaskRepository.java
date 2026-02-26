package com.challenge.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.challenge.todo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {   
}
