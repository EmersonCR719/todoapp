package com.code.todoapp.persistence.repository;

import com.code.todoapp.persistence.entity.Task;
import com.code.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED=TRUE WHERE ID = :id", nativeQuery = true)
    void markTaskAsFinished(@Param("id") Long id);

}