package com.docencia.pgv.soap;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.docencia.pgv.interfaces.AutorService;
import com.docencia.pgv.modelo.Autor;
import jakarta.jws.WebService;

@WebService(
    serviceName = "AutorService",
    portName = "AutorPort",
    targetNamespace = "http://autor.ies.puerto.es/",
    endpointInterface = "com.docencia.pgv.soap.AutorSoapService"
)
@Service
public class AutorSoapServiceImpl implements AutorSoapService {

    private final AutorService autorService;

    public AutorSoapServiceImpl(AutorService autorService) {
        this.autorService = autorService;
    }

    @Override public List<Autor> getAllAuthors() { 
        return autorService.findAll();
    }

    @Override public Autor getAuthorById(Long id) { 
        return autorService.findByIdOrThrow(id);
    }

    @Override public Autor createAuthor(String nombre, String pais) { 
        return autorService.create(new Autor(UUID.randomUUID().getMostSignificantBits(),nombre,pais));
    }

    @Override public void deleteAuthor(Long id) { 
        autorService.delete(id);
    }
}
