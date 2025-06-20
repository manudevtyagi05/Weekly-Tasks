package com.app.todo.controller;



import com.app.todo.dto.TaskDTO;
import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Convert Entity to DTO
    private TaskDTO convertToDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    // Convert DTO to Entity
    private Task convertToEntity(TaskDTO dto) {
        return modelMapper.map(dto,Task.class);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto) {
        Task task = convertToEntity(dto);
        Task saved = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok(convertToDTO(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable int id, @RequestBody TaskDTO updatedDTO) {
        return taskRepository.findById(id).map(task -> {
            task.setDescription(updatedDTO.getDescription());
            task.setCompleted(updatedDTO.isCompleted());
            Task saved = taskRepository.save(task);
            return ResponseEntity.ok(convertToDTO(saved));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public Page<TaskDTO> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return taskPage.map(this::convertToDTO);
    }
}
