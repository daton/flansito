package org.gelatina.flansito;

import org.springframework.data.annotation.Id;

public class Clave {

   @Id
   String token;
    String nombre;

    @Override
    public String toString() {
        return "Clave{" +

                ", token='" + token + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public Clave() {
    }



    public Clave(String token, String nombre) {
        this.token = token;
        this.nombre = nombre;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}