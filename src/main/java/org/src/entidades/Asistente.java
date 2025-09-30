package org.src.entidades;

import java.util.concurrent.atomic.AtomicInteger;

public class Asistente extends Persona{
    private static final AtomicInteger SEQ = new AtomicInteger(1000);


    public Asistente(String nombre, String apellido, String email) {
        super(nombre, apellido, email);
    }

    @Override
    protected Integer generarLegajo() {
        return SEQ.getAndIncrement();
    }
}
