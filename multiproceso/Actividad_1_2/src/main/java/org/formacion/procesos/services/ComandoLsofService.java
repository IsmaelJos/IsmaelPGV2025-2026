package org.formacion.procesos.services;


import org.formacion.procesos.domain.ProcessType;
import org.formacion.procesos.services.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;

@Component
public class ComandoLsofService extends ComandoServiceAbstract{

    public ComandoLsofService(){
        this.setTipo(ProcessType.LSOF);
        this.setExprecionRegular("^(-i)$");    }
}
