package com.application.ToDoApp.repository;

import com.application.ToDoApp.model.ToDoMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToDoMasterRepository extends JpaRepository<ToDoMaster, Long> {

//    @Query("SELECT m FROM ToDoMaster m ORDER BY (SELECT MIN(c.isChecked) FROM m.toDoTaskList c)")
//    List<ToDoMaster> findAllOrderByIsChecked();

}
