package com.application.ToDoApp.controller;

import com.application.ToDoApp.dto.ToDoDto;
import com.application.ToDoApp.model.ToDoMaster;
import com.application.ToDoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping({"/viewToDoList"})
    public List<ToDoMaster> viewAllToDoItems() {
        return service.getAllToDoItems();
    }

    @PostMapping({"/createToDoList"})
    public List<ToDoMaster> createToDoList(@RequestBody ToDoMaster master) {
        return service.createToDoList(master);
    }

    @PostMapping({"/updateIsPinned"})
    public List<ToDoMaster> updateIsPinned(@RequestBody ToDoMaster master) {
        return service.updateIsPinned(master);
    }

    @PostMapping({"/insertTasks"})
    public List<ToDoMaster> insertTasks(@RequestBody ToDoDto toDoDto) {
        return service.insertTasks(toDoDto);
    }

    @PostMapping({"/deleteTasks"})
    public List<ToDoMaster> deleteTasks(@RequestBody ToDoDto toDoDto) {
        return service.deleteTasks(toDoDto);
    }

    @PostMapping({"/deleteMaster"})
    public List<ToDoMaster> deleteMaster(@RequestBody ToDoDto toDoDto) {
        return service.deleteMaster(toDoDto);
    }

    @PostMapping({"/checkedTask"})
    public List<ToDoMaster> checkedTask(@RequestBody ToDoDto toDoDto) {
        return service.checkedTask(toDoDto);
    }

}
