package com.docencia.pgv.soap;

import java.util.List;
import com.docencia.pgv.modelo.Libro;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(
    targetNamespace="http://tareas.ies.puerto.es/",
    name="TareaPorType"
)
public interface LibroSoapService {
    
    @WebMethod(operationName = "getAllBooks")
    List<Libro> getAllBooks();
    
    @WebMethod(operationName = "getBookById")
    Libro getBookById(@WebParam(name = "id") Long id);
    
    @WebMethod(operationName = "getBooksByAuthor")
    List<Libro> getBooksByAuthor(@WebParam(name = "idAutor") Long idAutor);
    
    @WebMethod(operationName = "createBook")
    Libro createBook(@WebParam(name = "titulo") String titulo,
                     @WebParam(name = "anioPublicacion") Integer anioPublicacion,
                     @WebParam(name = "idAutor") Long idAutor);

    @WebMethod(operationName = "deleteBook")
    void deleteBook(@WebParam(name = "id") Long id);
}
