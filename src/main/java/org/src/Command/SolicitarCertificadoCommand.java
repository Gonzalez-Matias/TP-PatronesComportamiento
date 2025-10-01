package org.src.Command;

import org.src.Iterator.InscripcionIterator;
import org.src.entidades.Alumno;
import org.src.entidades.Curso;
import org.src.entidades.EstadoInscripcion;
import org.src.entidades.Inscripcion;

public class SolicitarCertificadoCommand implements Command{
    private Curso curso;
    private Alumno alumno;

    public SolicitarCertificadoCommand(Curso curso, Alumno alumno){
        this.curso = curso;
        this.alumno = alumno;
    }

    @Override
    public void execute() {
        InscripcionIterator inscripciones = alumno.inscripcionesIterator();
        while (inscripciones.hasNext()){
            Inscripcion inscripcion = inscripciones.next();
            if (inscripcion.getEstado() != EstadoInscripcion.CANCELADO && inscripcion.getCurso() == this.curso) {
                System.out.println("Certificado del Alumno " + this.alumno.getLegajo() + " en la materia " + this.curso.getMateria() + " generado.");
            }
        }
    }
}
