package com.application.ToDoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDto {

    Long todoId;

    Long taskId;

    boolean isChecked;

    String content ;

}
