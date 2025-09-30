package org.src.entidades;

public enum EstadoInscripcion {
    INSCRITO("Inscrito"),
    EN_ESPERA("EnEspera"),
    CANCELADO("Cancelado");

    private final String descripcion;

    EstadoInscripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){return this.descripcion;}
}
