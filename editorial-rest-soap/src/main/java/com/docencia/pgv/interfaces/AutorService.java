package com.docencia.pgv.interfaces;

import java.util.List;
import com.docencia.pgv.modelo.Autor;

public interface AutorService {
    List<Autor> findAll();
    Autor findByIdOrThrow(Long id);
    Autor create(Autor autor);
    void delete(Long id);
}
