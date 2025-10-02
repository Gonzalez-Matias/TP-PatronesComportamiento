package org.src.entidades;

import org.src.Memento.Memento;

import java.time.LocalDateTime;

public class Examen {
    private Inscripcion inscripcion;
    private Integer nota;
    private LocalDateTime fechaHoraExamen;
    private int numExamen;

    public Examen(Inscripcion inscripcion, Integer nota, LocalDateTime fechaHoraExamen, int numExamen){
        this.inscripcion = inscripcion;
        this.inscripcion.agregarExamen(this);
        this.nota = nota;
        this.fechaHoraExamen = fechaHoraExamen;
        this.numExamen = numExamen;
    }

    public Integer getNota() { return this.nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public Memento save(){
        return new Memento(this.nota, this.fechaHoraExamen,this.numExamen);
    }

    public void restore(Memento m){
        this.nota = m.nota;
        this.fechaHoraExamen = m.fechaHoraExamen;
        this.numExamen = m.numExamen;
    }

}
