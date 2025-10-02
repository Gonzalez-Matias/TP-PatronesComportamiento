package org.src.Command;

import org.src.Iterator.InscripcionIterator;
import org.src.State.Cancelado;
import org.src.entidades.Alumno;
import org.src.entidades.Curso;
import org.src.entidades.EstadoInscripcion;
import org.src.entidades.Inscripcion;

public class AbandonarCursoCommand implements Command{
    private Curso curso;
    private Alumno alumno;

    public AbandonarCursoCommand(Curso curso, Alumno alumno){
        this.curso = curso;
        this.alumno = alumno;
    }

    @Override
    public void execute() {
        InscripcionIterator inscripciones = alumno.inscripcionesIterator();
        while (inscripciones.hasNext()){
            Inscripcion inscripcion = inscripciones.next();
            if (inscripcion.getEstado().getClass() != Cancelado.class && inscripcion.getCurso() == this.curso){
                inscripcion.setEstado(new Cancelado());
            }
        }
        curso.removerAlumno(this.alumno);
        System.out.println("El Alumno " + this.alumno.getLegajo() + " abandono la materia " + this.curso.getMateria());
    }
}
