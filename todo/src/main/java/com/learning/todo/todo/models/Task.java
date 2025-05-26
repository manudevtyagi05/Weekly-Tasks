package com.learning.todo.todo.models;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Task {

    private int id;
    private String description;
    private  boolean completed;

    public void add(Task task) {
    }
}
