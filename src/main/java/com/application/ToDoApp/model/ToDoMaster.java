package com.application.ToDoApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ToDoMaster")
public class ToDoMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date createdDate;

    private Date updatedDate;

    @Column(nullable = false)
    private boolean isPinned;

    @Column(nullable = false)
    private String userId;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toDoMaster", cascade = CascadeType.ALL)
    private List<ToDoTask> toDoTaskList;
}
