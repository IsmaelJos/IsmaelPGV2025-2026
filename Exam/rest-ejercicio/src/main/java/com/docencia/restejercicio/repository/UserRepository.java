package com.docencia.restejercicio.repository;

import com.docencia.restejercicio.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    List<User> listUsers = new ArrayList<>();

    public UserRepository() {
    }

    public List<User> findAll() {
        List<User> newList = new ArrayList<>();
        for (User user : listUsers) {
            newList.add(user);
        }
        return newList;
    }

    public Optional<User> findById(Long id) {
        for (User user : listUsers) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }
        return null;
    }

    public User save(User user) {
        listUsers.add(user);
        return user;
    }

    public void deleteById(Long id) {
        listUsers.remove(id);
    }

    public boolean existsById(Long id) {
        for (User user : listUsers) {
            if (user.getId()==id) {
                return true;
            }
        }
        return false;
    }
}
