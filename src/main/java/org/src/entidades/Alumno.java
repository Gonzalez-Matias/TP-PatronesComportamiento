package org.src.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Alumno extends Persona {
    private static final AtomicInteger SEQ = new AtomicInteger(100000);
    private List<Inscripcion> inscripciones = new ArrayList<>();
    private List<SolicitudTutoria> solicitudes = new ArrayList<>();

    public Alumno(String nombre, String apellido, String email) {
        super(nombre, apellido, email);
    }

    @Override
    protected Integer generarLegajo() {
        return SEQ.getAndIncrement();
    }

    public void agregarInscripcion(Inscripcion inscripcion){
        inscripciones.add(inscripcion);
    }

    public void agregarSolicitudTutoria(SolicitudTutoria solicitud){
        solicitudes.add(solicitud);
    }
}
