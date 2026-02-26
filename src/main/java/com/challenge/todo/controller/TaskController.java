package com.challenge.todo.controller;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.challenge.todo.model.Task;
import com.challenge.todo.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
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

    @GetMapping("/{id}")
    public Task get(@PathVariable @NonNull Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Task updatedTask) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
            task.setName(updatedTask.getName());
            task.setDescription(updatedTask.getDescription());
            task.setDone(updatedTask.getDone());
            task.setPriority(updatedTask.getPriority());

            return repository.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NonNull Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        repository.deleteById(id);
    }
}