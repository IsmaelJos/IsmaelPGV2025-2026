package com.docencia.pgv.servicios;

import java.util.List;
import org.springframework.stereotype.Service;
import com.docencia.pgv.interfaces.LibroService;
import com.docencia.pgv.modelo.Libro;
import com.docencia.pgv.repositorios.interfaces.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {
    
    LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override public List<Libro> findAll() { 
        return libroRepository.findAll();
    }
    @Override public Libro findByIdOrThrow(Long id) { 
        if (libroRepository.findById(id) == null) {
            throw new IllegalArgumentException("ID missing");
        }
        return libroRepository.findById(id).orElse(null);
    }
    @Override public List<Libro> findByAutorOrThrow(Long idAutor) { 
        if (libroRepository.findByIdAutor(idAutor) == null) {
            throw new IllegalArgumentException("ID missing");
        }
        return libroRepository.findByIdAutor(idAutor);
    }
    @Override public Libro create(Libro libro) { 
        return libroRepository.save(libro);
    }
    @Override public void delete(Long id) { 
        libroRepository.deleteById(id);
    }
}
