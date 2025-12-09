package com.docencia.restejercicio.service;

import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.repository.TaskRepository;


import java.util.List;

public class TaskService {

    private  final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public Task update(Long id, Task update) {
        Task updateTask = new Task();
        if (repository.existsById(id)) {
            updateTask = repository.save(update);
        }
        return updateTask;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
