package com.docencia.tareas.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.docencia.tareas.model.Tarea;

@Repository
public class TareaRepository implements ITareaRepository {
    private final List<Tarea> tareas;

    public TareaRepository(List<Tarea> tareas) {
        Tarea t1 = new Tarea(1l,"Estudiar TypeScript","Repasar tipos y funciones",false);
        Tarea t2 = new Tarea(2l,"Hacer la práctica 1","Proyecto tareas en memoria",true);
        this.tareas = new ArrayList<>();
        tareas.add(t1);
        tareas.add(t2);
    }

    @Override
    public Tarea add(Tarea tarea) {
        if (!tareas.contains(tarea)) {
            return tarea;
        }
        tareas.add(tarea);
        return tarea;
    }

    @Override
    public Boolean delete(Tarea tarea) {
        return tareas.remove(tarea);
    }

    @Override
    public Tarea findById(Tarea tarea) {
        if (!tareas.contains(tarea)) {
            return null;
        }
        int posicion = tareas.indexOf(tarea);
        return tareas.get(posicion);
    }

    @Override
    public Tarea update(Tarea tarea) {
        int posicion = tareas.indexOf(tarea);
        if (posicion > -1) {
            tareas.set(posicion, tarea);
        }
        return tarea;
    }

    @Override
    public List<Tarea> all() {
        return tareas;
    }
    
}
