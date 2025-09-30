package org.src.entidades;
import java.util.*;

public class Curso {
    private Profesor profesor;
    private Materia materia;
    private Set<Alumno> alumnos = new HashSet<>();
    private String comision;
    private String turno;

    public Curso(Materia materia, String comision, String turno){
        this.materia = materia;
        this.comision = comision;
        this.turno = turno;
    }
    public void setProfesor(Profesor profesor){
        this.profesor = profesor;
    }

    public Set<Alumno> getAlumnos() {return Collections.unmodifiableSet(alumnos);
    }

    public Materia getMateria() {
        return materia;
    }

    public String getComision() {
        return comision;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public String getTurno() {
        return turno;
    }

    public void agregarAlumno(Alumno alumno){
        alumnos.add(alumno);
    }

    public boolean removerAlumno(Alumno alumno){
        return alumnos.remove(alumno);
    }
}
