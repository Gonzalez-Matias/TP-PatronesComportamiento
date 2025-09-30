package org.src.entidades;

public enum EstadoSolicitudTutoria {
    ENVIADA("Enviada"),
    REVISADA_POR_ASISTENTE("Revisada por Asistente"),
    REVISADA_POR_PROFESOR("Revisada por Profesor"),
    APROBADA("Aprobada"),
    RECHAZADA("Rechazada"),
    FINALIZADA("Finalizada"),;
    private final String descripcion;

    EstadoSolicitudTutoria(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){return this.descripcion;}
}
