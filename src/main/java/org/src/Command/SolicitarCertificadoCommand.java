package org.src.Command;

import org.src.entidades.Alumno;
import org.src.entidades.Curso;
import org.src.entidades.EstadoInscripcion;
import org.src.entidades.Inscripcion;

import java.util.List;

public class SolicitarCertificadoCommand implements Command{
    private Curso curso;
    private Alumno alumno;

    public SolicitarCertificadoCommand(Curso curso, Alumno alumno){
        this.curso = curso;
        this.alumno = alumno;
    }

    @Override
    public void execute() {
        List<Inscripcion> inscripciones = alumno.getInscripciones();
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getEstado() != EstadoInscripcion.CANCELADO && inscripcion.getCurso() == this.curso) {
                System.out.println("Certificado del Alumno " + this.alumno.getLegajo() + " en la materia " + this.curso.getMateria() + " generado.");
            }
        }
    }
}
