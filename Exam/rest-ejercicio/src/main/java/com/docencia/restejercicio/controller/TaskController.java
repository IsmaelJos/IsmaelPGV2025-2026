package com.docencia.restejercicio.controller;

import com.docencia.restejercicio.common.ApiRestController;
import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.model.User;
import com.docencia.restejercicio.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Tag(name = "Tasks", description = "API de gestion de tareas")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @Operation(summary = "Listar todos las tareas")
    @GetMapping("/users")
    public List<Task> getAllTask() {
        return taskService.getAll();
    }

    @Operation(summary = "Obtener una tarea por id")
    @GetMapping("/users/{id}")
    public Task getTaskById(Long userId){
         return taskService.getById(userId);
    }

    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/users/{id}")
    public ResponseEntity<Task> updateTask(Long taskId, Task taskDetails) {
        final Task updatedTask = taskService.update(taskId, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }
}
