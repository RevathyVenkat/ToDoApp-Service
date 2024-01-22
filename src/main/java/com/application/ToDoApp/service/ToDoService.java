package com.application.ToDoApp.service;

import com.application.ToDoApp.dto.ToDoDto;
import com.application.ToDoApp.model.ToDoMaster;
import com.application.ToDoApp.model.ToDoTask;
import com.application.ToDoApp.repository.ToDoMasterRepository;
import com.application.ToDoApp.repository.ToDoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    @Autowired
    ToDoMasterRepository toDoMasterRepository;

    @Autowired
    ToDoTaskRepository toDoTaskRepository;

    public List<ToDoMaster> getAllToDoItems() {
        return getAllItems();
    }

    public List<ToDoMaster> getAllItems(){
        return toDoMasterRepository.findAll().stream()
                .peek(master -> master.getToDoTaskList().sort(Comparator.comparing(ToDoTask::isChecked)))
                .sorted(Comparator.comparing(ToDoMaster::isPinned).reversed())
                .collect(Collectors.toList());
    }
    public List<ToDoMaster> createToDoList(ToDoMaster master) {
        ToDoMaster toDoMaster;
        if(master.getTodoId() !=null){
            toDoMaster = toDoMasterRepository.findById(master.getTodoId()).orElse(null);
            assert toDoMaster != null;
            toDoMaster.setTitle(master.getTitle());
        }else {
            toDoMaster = new ToDoMaster();
            toDoMaster.setPinned(master.isPinned());
            toDoMaster.setCreatedDate(new Date());
            toDoMaster.setUpdatedDate(new Date());
            toDoMaster.setTitle(master.getTitle());
            toDoMaster.setToDoTaskList(new ArrayList<>());
            toDoMaster.setUserId(master.getUserId());
        }
        toDoMasterRepository.save(toDoMaster);
        return getAllItems();
    }

    public List<ToDoMaster> updateIsPinned(ToDoMaster master) {
        ToDoMaster toDoMaster = toDoMasterRepository.findById(master.getTodoId()).orElse(null);
        assert toDoMaster != null;
        toDoMaster.setPinned(!toDoMaster.isPinned());
        toDoMasterRepository.save(toDoMaster);
        return getAllItems();
    }

    public List<ToDoMaster> insertTasks(ToDoDto toDoDto) {
        if(toDoDto.getTaskId() !=null) {
            ToDoTask toDoTask = toDoTaskRepository.findById(toDoDto.getTaskId()).orElse(null);
            assert toDoTask != null;
            toDoTask.setContent(toDoDto.getContent());
            toDoTask.setUpdatedDate(new Date());
            toDoTask.setChecked(toDoDto.isChecked());
            toDoTaskRepository.save(toDoTask);
        } else {
            ToDoTask newToDo = new ToDoTask();
            newToDo.setChecked(toDoDto.isChecked());
            newToDo.setCreatedDate(new Date());
            newToDo.setUpdatedDate(new Date());
            newToDo.setOrderId(0);
            newToDo.setToDoMaster(toDoMasterRepository.findById(toDoDto.getTodoId()).orElse(null));
            newToDo.setContent(toDoDto.getContent());
            toDoTaskRepository.save(newToDo);
        }
        return getAllItems();
    }

    public List<ToDoMaster> deleteTasks(ToDoDto toDoDto) {
        toDoTaskRepository.deleteById(toDoDto.getTaskId());
        return getAllItems();
    }

    public List<ToDoMaster> deleteMaster(ToDoDto toDoDto) {
        toDoMasterRepository.deleteById(toDoDto.getTodoId());
        return getAllItems();
    }

    public List<ToDoMaster> checkedTask(ToDoDto toDoDto) {
        ToDoTask task = toDoTaskRepository.findById(toDoDto.getTaskId()).orElse(null);
        assert task != null;
        task.setChecked(!task.isChecked());
        toDoTaskRepository.save(task);
        return getAllItems();
    }
}
