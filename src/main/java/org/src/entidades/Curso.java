package org.src.entidades;
import java.util.*;
import org.src.Observer.CursoObserver;

public class Curso {
    private Profesor profesor;
    private Materia materia;
    private Set<Alumno> alumnos = new HashSet<>();
    private String comision;
    private String turno;
    private List<CursoObserver> observers = new ArrayList<>();

    public Curso(Materia materia, String comision, String turno){
        this.materia = materia;
        this.comision = comision;
        this.turno = turno;
    }
    public void setProfesor(Profesor profesor){

        this.profesor = profesor;
        notifyObservers("Profesor " + profesor.nombre + " " + profesor.apellido + " asignado al curso: " + this.materia);
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

    public void addObserver(CursoObserver o){
        observers.add(o);
    }

    public void removeObserver(CursoObserver o){
        observers.remove(o);
    }

    public void notifyObservers(String mensaje){
        for (CursoObserver o : observers) {
            o.update("[" + materia.getNombre() + " - " + comision + "] " + mensaje);
        }
    }

    public void nuevoAviso(String aviso) {
        notifyObservers("Aviso importante: " + aviso);
    }
}
