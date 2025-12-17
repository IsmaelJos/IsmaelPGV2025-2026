package com.docencia.pgv.soap;

import java.util.List;
import com.docencia.pgv.modelo.Autor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(
    targetNamespace="http://alumno.ies.puerto.es/",
    name="AlumnoPorType"
)
public interface AutorSoapService {
    
    @WebMethod(operationName = "getAllAuthors")
    List<Autor> getAllAuthors();
    
    @WebMethod(operationName = "getAuthorById")
    Autor getAuthorById(@WebParam(name = "id") Long id);
    
    @WebMethod(operationName = "createAuthor")
    Autor createAuthor(@WebParam(name = "nombre") String nombre, @WebParam(name = "pais") String pais);
    
    @WebMethod(operationName = "deleteAuthor")
    void deleteAuthor(@WebParam(name = "id") Long id);
}
