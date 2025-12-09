package com.docencia.tareas.model;

import java.util.List;

public class TareasResponse {

    private List<Tarea> tareas;

    public TareasResponse() {
    }

    public TareasResponse(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
}