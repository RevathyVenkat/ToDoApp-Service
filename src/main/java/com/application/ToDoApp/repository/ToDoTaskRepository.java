package com.application.ToDoApp.repository;

import com.application.ToDoApp.model.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoTaskRepository  extends JpaRepository<ToDoTask, Long> {


}
