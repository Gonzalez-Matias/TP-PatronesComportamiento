package org.src.entidades;

import org.src.Memento.Memento;

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

    public float getNota() { return this.nota; }
    public void setNota(float nota) { this.nota = nota; }

    public Memento save(){
        return new Memento(this.nota, this.fechaHoraExamen,this.numExamen);
    }

    public void restore(Memento m){
        this.nota = m.nota;
        this.fechaHoraExamen = m.fechaHoraExamen;
        this.numExamen = m.numExamen;
    }

}
