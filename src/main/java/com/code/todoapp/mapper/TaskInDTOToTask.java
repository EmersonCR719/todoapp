package com.code.todoapp.mapper;

import com.code.todoapp.persistence.entity.Task;
import com.code.todoapp.persistence.entity.TaskStatus;
import com.code.todoapp.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task>{

    @Override
    public Task map(TaskInDTO in) {
        Task task = new Task();
        task.setTittle(in.getTittle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreateDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
