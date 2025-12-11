package com.docencia.tareas.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.docencia.tareas.soap.IAlumnoSoapEndpoint;
import com.docencia.tareas.soap.ITareaSoapEndpoint;

import jakarta.xml.ws.Endpoint;


@Configuration
public class CxfConfig {

    private final Bus bus;
    private final ITareaSoapEndpoint tareaSoapEndpoint;
    private final IAlumnoSoapEndpoint alumnoSoapEndpoint;

    public CxfConfig(Bus bus, ITareaSoapEndpoint tareaSoapEndpoint, IAlumnoSoapEndpoint alumnoSoapEndpoint) {
        this.bus = bus;
        this.tareaSoapEndpoint = tareaSoapEndpoint;
        this.alumnoSoapEndpoint = alumnoSoapEndpoint;
    }

    @Bean
    public Endpoint tareaEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, tareaSoapEndpoint);
        endpoint.publish("/tareas");
        return endpoint;
    }
    @Bean
    public Endpoint alumnoEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, alumnoSoapEndpoint);
        endpoint.publish("/alumnos");
        return endpoint;
    }
}