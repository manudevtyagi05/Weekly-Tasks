package com.app.todo.service;

import com.app.todo.dto.TaskDTO;
import com.app.todo.exception.ResourceNotFoundException;
import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repo;

    @Autowired
    private ModelMapper mapper;

    public TaskDTO getTaskById(int id) {
        Task task = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task ID " + id + " not found"));
        return mapper.map(task, TaskDTO.class);
    }

    public TaskDTO updateTask(int id, TaskDTO dto) {
        Task task = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task ID " + id + " not found"));

        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        return mapper.map(repo.save(task), TaskDTO.class);
    }

    public void deleteTask(int id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Task ID " + id + " not found");
        }
        repo.deleteById(id);
    }

    public TaskDTO createTask(TaskDTO dto) {
        Task task = mapper.map(dto, Task.class);
        return mapper.map(repo.save(task), TaskDTO.class);
    }

    public Page<TaskDTO> getAllTasks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repo.findAll(pageable).map(task -> mapper.map(task, TaskDTO.class));
    }
}
