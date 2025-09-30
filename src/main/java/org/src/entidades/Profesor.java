package org.src.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Profesor extends Persona {
    private static final AtomicInteger SEQ = new AtomicInteger(10000);
    private List<Curso> cursos = new ArrayList<>();
    private List<Materia> materias = new ArrayList<>();
    protected boolean esCoordinador = false;


    public Profesor(String nombre, String apellido, String email) {
        super(nombre, apellido, email);
    }

    @Override
    protected Integer generarLegajo() {
        return SEQ.getAndIncrement();
    }
}
