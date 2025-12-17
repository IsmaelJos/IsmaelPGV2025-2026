package com.docencia.pgv.soap;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.docencia.pgv.interfaces.LibroService;
import com.docencia.pgv.modelo.Libro;
import jakarta.jws.WebService;

@WebService(
    serviceName = "LibroService",
    portName = "LibroPort",
    targetNamespace = "http://libros.ies.puerto.es/",
    endpointInterface = "com.docencia.pgv.soap.LibroSoapService"
)
@Service
public class LibroSoapServiceImpl implements LibroSoapService {

    private final LibroService libroService;

    public LibroSoapServiceImpl(LibroService libroService) {
        this.libroService = libroService;
    }

    @Override public List<Libro> getAllBooks() { 
        return libroService.findAll();
    }
    @Override public Libro getBookById(Long id) { 
        return libroService.findByIdOrThrow(id);
    }
    @Override public List<Libro> getBooksByAuthor(Long idAutor) { 
        return libroService.findByAutorOrThrow(idAutor);
    }
    @Override public Libro createBook(String titulo, Integer anioPublicacion, Long idAutor) { 
        return libroService.create(new Libro(UUID.randomUUID().getMostSignificantBits(),titulo,anioPublicacion,idAutor)); 
    }
    @Override public void deleteBook(Long id) { 
        libroService.delete(id);
    }
}
