package com.docencia.restejercicio.controller;

import com.docencia.restejercicio.model.User;
import com.docencia.restejercicio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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

@Tag(name = "Usuarios", description = "API de gestion de usuarios")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @Operation(summary = "Obtener un usuario por id")
    @GetMapping("/users/{id}")
    public User getUserById(Long userId){
         return userService.getById(userId);
    }

    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(Long userId, User userDetails) {
        final User updatedUser = userService.update(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
    

}
