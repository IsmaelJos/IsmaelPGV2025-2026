package org.formacion.procesos.repository;

import org.springframework.stereotype.Repository;

@Repository("baseDatosRepository")
public class BaseDatosRepository implements IFicheroRepository {

    @Override
    public String saludar() {
        return "Salidando desde el repositorio de BBDD";
    }

}
