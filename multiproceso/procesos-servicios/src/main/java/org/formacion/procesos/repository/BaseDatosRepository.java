package org.formacion.procesos.repository;

import org.formacion.procesos.repository.interfaces.IAlmacenamientoRepository;
import org.springframework.stereotype.Repository;

@Repository("baseDatosRepository")
public class BaseDatosRepository implements IAlmacenamientoRepository {

    @Override
    public String saludar() {
        return "Salidando desde el repositorio de BBDD";
    }

}
