package com.docencia.pgv.modelo;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "autor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Autor {

    private Long id;
    private String nombre;
    private String pais;

    public Autor() {}

    public Autor(Long id) {
        this.id = id;
    }

    public Autor(Long id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPais() { return pais; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPais(String pais) { this.pais = pais; }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Autor)) {
            return false;
        }
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", pais='" + getPais() + "'" +
            "}";
    }
    
}
