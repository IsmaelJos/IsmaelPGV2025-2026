package com.docencia.tareas.repositories;

import java.util.List;

import com.docencia.tareas.model.Tarea;

public interface ITareaRepository {

    /**
     * coment
     * @param tarea
     * @return
     */
    public Tarea add(Tarea tarea);
    public Boolean delete(Tarea tarea);
    public Tarea findById(Tarea tarea);
    public Tarea update(Tarea tarea);
    public List<Tarea> all();

}
