package com.app.todo.controller;


import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository repo;

    public TaskController( TaskRepository repo) {
        this.repo = repo; 
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task saved = repo.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Task> getAllTask() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id){
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task update) {
        return repo.findById(id)
                .map(task -> {
                    task.setTitle(update.getTitle());
                    task.setCompleted(update.isCompleted());
                    return ResponseEntity.ok(repo.save(task));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable int id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
