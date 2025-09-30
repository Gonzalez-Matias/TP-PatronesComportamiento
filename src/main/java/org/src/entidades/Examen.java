package org.src.entidades;

import java.time.LocalDateTime;

public class Examen {
    private Inscripcion inscripcion;
    private float nota;
    private LocalDateTime fechaHoraExamen;
    private int numExamen;

    public Examen(Inscripcion inscripcion, float nota, LocalDateTime fechaHoraExamen, int numExamen){
        this.inscripcion = inscripcion;
        this.inscripcion.agregarExamen(this);
        this.nota = nota;
        this.fechaHoraExamen = fechaHoraExamen;
        this.numExamen = numExamen;
    }
}
