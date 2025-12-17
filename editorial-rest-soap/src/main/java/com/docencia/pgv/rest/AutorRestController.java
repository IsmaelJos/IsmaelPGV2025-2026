package com.docencia.pgv.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.docencia.pgv.interfaces.AutorService;
import com.docencia.pgv.modelo.Autor;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/autors")
public class AutorRestController {

    private final AutorService autorService;

    public AutorRestController(AutorService autorService) {
        this.autorService = autorService;
    }

    // GET /api/autors
    // Devuelve 200 OK con la lista de autors
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping
    public List<Autor> listarTodas() {
        return autorService.findAll();
    }
    
    // GET /api/autors/{id}
    // 200 OK con el autor. 404 Not Found si no existe.
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        Autor autor = autorService.findByIdOrThrow(id);
        if (autor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    // POST /api/autors
    @PostMapping("")
    public Autor createAutor(@Valid @RequestBody Autor autor) {
        return autorService.create(autor);
    }

    // DELETE /api/autors/{id}
    // 204 No Content si se borra correctamente.
    // 404 Not Found si no existe.
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
