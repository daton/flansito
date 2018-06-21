package org.gelatina.flansito;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Mensaje {

    @Id
    String id;

    String titulo;

    public Mensaje(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }

    public Mensaje(String titulo) {
        this.titulo = titulo;
    }

    public Mensaje() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
