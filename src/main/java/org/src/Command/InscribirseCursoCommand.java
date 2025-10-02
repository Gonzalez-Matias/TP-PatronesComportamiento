package org.src.Command;

import org.src.entidades.Alumno;
import org.src.entidades.Curso;
import org.src.entidades.Inscripcion;

public class InscribirseCursoCommand implements Command{
    private Curso curso;
    private Alumno alumno;

    public InscribirseCursoCommand(Curso curso, Alumno alumno){
        this.curso = curso;
        this.alumno = alumno;
    }

    @Override
    public void execute() {
        new Inscripcion(alumno, curso);
        curso.agregarAlumno(alumno);
        System.out.println("El Alumno " + this.alumno.getLegajo() + " se inscribio en la materia " + this.curso.getMateria());
    }
}
