package org.formacion.procesos.repository;

import org.formacion.procesos.repository.interfaces.IAlmacenamientoRepository;
import org.springframework.stereotype.Repository;

@Repository("ficheroRepository")
public class FicheroRepository implements IAlmacenamientoRepository {

    @Override
    public String saludar() {
        return "Salidando desde el repositorio fichero";
    }

}
