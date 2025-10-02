package org.src.entidades;

import org.src.State.EnEspera;

import org.src.State.EstadoInscripcion;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Inscripcion {
    private Alumno alumno;
    private Curso curso;
    private Set<Examen> examenes = new HashSet<>();
    private EstadoInscripcion estado;
    private LocalDateTime fechaCreacion;

    public Inscripcion(Alumno alumno, Curso curso){
        this.alumno = alumno;
        alumno.agregarInscripcion(this);
        this.curso = curso;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = new EnEspera();
    }

    public void agregarExamen(Examen examen){
        examenes.add(examen);
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Set<Examen> getExamenes() {
        return Collections.unmodifiableSet(examenes);
    }

    public void aceptarAlumno (){
        curso.agregarAlumno(alumno);
        estado.inscribir(this);
    }

    public void rechazarAlumno (){
        curso.removerAlumno(alumno);
        estado.cancelar(this);
    }
}
