package org.gelatina.flansito;


public class Estatus {


    String mensaje;
    boolean success;

    public Estatus() {
    }

    public Estatus(String mensaje, boolean success) {
        this.mensaje = mensaje;
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
