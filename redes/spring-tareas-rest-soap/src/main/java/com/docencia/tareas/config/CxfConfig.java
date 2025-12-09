package com.docencia.tareas.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.docencia.tareas.soap.ITareaSoapEndpoint;

import jakarta.xml.ws.Endpoint;


@Configuration
public class CxfConfig {

    private final Bus bus;
    private final ITareaSoapEndpoint tareaSoapEndpoint;

    public CxfConfig(Bus bus, ITareaSoapEndpoint tareaSoapEndpoint) {
        this.bus = bus;
        this.tareaSoapEndpoint = tareaSoapEndpoint;
    }

    @Bean
    public Endpoint tareaEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, tareaSoapEndpoint);
        endpoint.publish("/tareas");
        return endpoint;
    }
}