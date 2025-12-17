package com.docencia.pgv.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.docencia.pgv.interfaces.LibroService;
import com.docencia.pgv.modelo.Libro;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
public class LibroRestController {

    private final LibroService libroService;

    public LibroRestController(LibroService libroService) {
        this.libroService = libroService;
    }
    
    // GET /api/libros
    // Devuelve 200 OK con la lista de libros
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping
    public List<Libro> listarTodas() {
        return libroService.findAll();
    }
    
    // GET /api/libros/{id}
    // 200 OK con el libro. 404 Not Found si no existe.
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarPorId(@PathVariable Long id) {
        Libro libro = libroService.findByIdOrThrow(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }

    // GET /api/libros/autor/{idAutor}
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/autor/{idAutor}")
    public List<Libro> buscarPorIdAutor(@PathVariable Long idAutor) {
        return libroService.findByAutorOrThrow(idAutor);
    }

    // POST /api/libros
    @PostMapping("")
    public Libro createLibro(@Valid @RequestBody Libro libro) {
        return libroService.create(libro);
    }

    // DELETE /api/libros/{id}
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
