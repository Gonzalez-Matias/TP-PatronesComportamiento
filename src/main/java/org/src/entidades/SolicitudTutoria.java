package org.src.entidades;

import java.time.LocalDateTime;

public class SolicitudTutoria {
    private Alumno solicitante;
    private Profesor tutor;
    private Materia materia;
    private EstadoSolicitudTutoria estado;
    private LocalDateTime fechaHoraInicioConsulta;
    private LocalDateTime fechaHoraFinConsulta;

    public SolicitudTutoria(Alumno alumno, Profesor profesor, Materia materia,
                            LocalDateTime fechaHoraInicioConsulta, LocalDateTime fechaHoraFinConsulta){
        this.solicitante = alumno;
        this.tutor = profesor;
        this.materia = materia;
        this.fechaHoraInicioConsulta = fechaHoraInicioConsulta;
        this.fechaHoraFinConsulta = fechaHoraFinConsulta;
        this.estado = EstadoSolicitudTutoria.ENVIADA;
    }

    public void setEstado(EstadoSolicitudTutoria estado){this.estado = estado;}

    public EstadoSolicitudTutoria getEstado() {
        return estado;
    }

    public LocalDateTime getFechaHoraFinConsulta() {
        return fechaHoraFinConsulta;
    }

    public LocalDateTime getFechaHoraInicioConsulta() {
        return fechaHoraInicioConsulta;
    }

    public Materia getMateria() {
        return materia;
    }
}
