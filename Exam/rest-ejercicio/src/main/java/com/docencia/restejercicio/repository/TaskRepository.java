package com.docencia.restejercicio.repository;

import com.docencia.restejercicio.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {

    List<Task> listTasks = new ArrayList<>();

    public TaskRepository() {
    }

    public List<Task> findAll() {
        return listTasks;
    }

    public Optional<Task> findById(Long id) {
        for (Task task : listTasks) {
            if (task.getId().equals(id)) {
                return Optional.of(task);
            }
        }
       return null;
    }

    public Task save(Task task) {
        listTasks.add(task);
        return task;
    }

    public void deleteById(Long id) {
        listTasks.remove(id);
    }

    public boolean existsById(Long id) {
        for (Task task : listTasks) {
            if (task.getId()==id) {
                return true;
            }
        }
        return false;
    }
}
