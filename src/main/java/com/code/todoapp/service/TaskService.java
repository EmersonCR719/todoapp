package com.code.todoapp.service;

import com.code.todoapp.exceptions.ToDoExceptions;
import com.code.todoapp.mapper.TaskInDTOToTask;
import com.code.todoapp.persistence.entity.Task;
import com.code.todoapp.persistence.entity.TaskStatus;
import com.code.todoapp.persistence.repository.TaskRepository;
import com.code.todoapp.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository taskRepository, TaskInDTOToTask mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus) {
        return this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.markTaskAsFinished(id);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }
}
