package org.src.TemplateMethod;

import org.src.entidades.Curso;

public class ReporteCurso extends ReporteAcademico{
    private Curso curso;

    public ReporteCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    protected void contenido() {
        System.out.println("Curso: " + curso.getComision());
        System.out.println("Cantidad de alumnos: " + curso.getAlumnos().size());
    }
}
