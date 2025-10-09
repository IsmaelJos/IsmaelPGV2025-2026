package org.formacion.procesos.component;

import org.formacion.procesos.component.interfaces.IFicheroComponent;
import org.formacion.procesos.repository.interfaces.IAlmacenamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FicheroComponent implements IFicheroComponent{

    @Autowired
    @Qualifier("ficheroRepository")
    IAlmacenamientoRepository ficheroRepository;

    @Autowired
    @Qualifier("baseDatosRepository")
    IAlmacenamientoRepository baseDatosRepository;

    @Override
    public String mensaje() {
        return ficheroRepository.saludar()+" \n"+baseDatosRepository.saludar();
    }

 

}
