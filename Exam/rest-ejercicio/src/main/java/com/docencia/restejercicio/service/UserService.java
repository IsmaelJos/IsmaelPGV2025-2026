package com.docencia.restejercicio.service;

import com.docencia.restejercicio.model.User;
import com.docencia.restejercicio.repository.UserRepository;
import java.util.List;

public class UserService {

    private final UserRepository repository;

    public UserService (UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(Long id, User update) {
        User updatUser = new User();
        if (repository.existsById(id)) {
            updatUser = repository.save(update);
        }
        return updatUser;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
