package com.app.todo.dto;

import lombok.Data;

@Data
public class TaskDTO {
    private int id;
    private String description;
    private boolean completed;
}
