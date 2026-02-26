package com.challenge.todo.controller;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.challenge.todo.model.Task;
import com.challenge.todo.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Task create(@Valid @RequestBody @NonNull Task task) {        
        return repository.save(task);
    }

    @GetMapping
    public List<Task> list() {
        return repository.findAll();
    }
}