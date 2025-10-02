package org.src.TemplateMethod;

import org.src.entidades.Alumno;
import org.src.entidades.Curso;


public class ReporteAlumno extends ReporteAcademico{
    public Alumno alumno;
    public Curso curso;

    public ReporteAlumno(Alumno alumno, Curso curso) {
        this.alumno = alumno;
        this.curso = curso;
    }

    @Override
    protected void contenido() {
        System.out.println("Alumno: " + alumno.getNombre());
        System.out.println("Curso: " + curso.getComision());
        System.out.printf("Nota final: %.2f%n", alumno.calcularNota(curso.getMateria()));
    }
}
