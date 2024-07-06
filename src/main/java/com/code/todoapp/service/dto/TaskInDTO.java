package com.code.todoapp.service.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskInDTO {
    private String tittle;
    private String description;
    private LocalDateTime eta;
}
