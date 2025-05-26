package com.learning.todo.todo.controller;

import com.learning.todo.todo.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/tasks")
public class TaskController {
 private List<Task> tasks = new ArrayList<>();
 private AtomicInteger idCounter = new AtomicInteger();

 @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
     task.setId(idCounter.incrementAndGet());
     task.add(task);
     return ResponseEntity.status(HttpStatus.CREATED).body(task);
 }

 @GetMapping
    public List<Task> getAllTasks() {
     return tasks;
 }

 @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
     return tasks.stream()
             .filter(t -> t.getId() == id)
             .findFirst()
             .map(ResponseEntity:: ok)
             .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
 }

 @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task updateTask) {
     for (Task task : tasks) {
         if (task.getId() == id) {
             task.setDescription(updateTask.getDescription());
             task.setCompleted(updateTask.isCompleted());
             return ResponseEntity.ok(task);
         }
     }
     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 }

 @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
     boolean removed = tasks.removeIf(t -> t.getId() == id );
     return removed ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 }


}
