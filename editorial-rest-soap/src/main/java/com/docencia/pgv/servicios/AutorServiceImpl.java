package com.docencia.pgv.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import com.docencia.pgv.interfaces.AutorService;
import com.docencia.pgv.modelo.Autor;
import com.docencia.pgv.repositorios.interfaces.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService {

    AutorRepository autorRepository;
    
    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override 
    public List<Autor> findAll() { 
        return autorRepository.findAll(); 
    }

    @Override 
    public Autor findByIdOrThrow(Long id) { 
        if (id == null) {
            throw new IllegalArgumentException("ID missing");
        }
        return autorRepository.findById(id).orElse(null);
    }

    @Override 
    public Autor create(Autor autor) { 
        return autorRepository.save(autor);
    }

    @Override 
    public void delete(Long id) { 
        autorRepository.deleteById(id);
    }
}
