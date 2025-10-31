package org.formacion.procesos.services;


import org.formacion.procesos.domain.ProcessType;
import org.formacion.procesos.services.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;

@Component
public class ComandoTopService extends ComandoServiceAbstract{

    public ComandoTopService(){
        this.setTipo(ProcessType.TOP);
        this.setExprecionRegular("^(-b\s-n1)$");    }


}
