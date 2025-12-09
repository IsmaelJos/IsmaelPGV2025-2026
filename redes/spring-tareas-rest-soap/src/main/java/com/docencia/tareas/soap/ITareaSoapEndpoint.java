package com.docencia.tareas.soap;

import java.util.List;

import com.docencia.tareas.model.Tarea;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(
    targetNamespace = "http://tareas.ies.puerto.es/",
    name = "TareaPortType"
)
public interface ITareaSoapEndpoint {

    @WebMethod(operationName = "ListAll")
    List<Tarea> listar();

    @WebMethod 
    Tarea buscar( @WebParam(name = "id") Long id);

    @WebMethod
    Tarea crear(String titulo, String descripcion);

    @WebMethod
    Tarea actualizar(Long id, String titulo, String descripcion, Boolean completada);

    @WebMethod
    boolean eliminar(Long id);
}
