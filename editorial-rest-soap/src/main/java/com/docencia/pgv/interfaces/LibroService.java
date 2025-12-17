package com.docencia.pgv.interfaces;

import java.util.List;
import com.docencia.pgv.modelo.Libro;

public interface LibroService {
    List<Libro> findAll();
    Libro findByIdOrThrow(Long id);
    List<Libro> findByAutorOrThrow(Long idAutor);
    Libro create(Libro libro);
    void delete(Long id);
}
