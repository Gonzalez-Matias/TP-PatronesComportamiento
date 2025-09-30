package org.src.entidades;

import org.src.Mediator.ChatMediator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Alumno extends Persona {
    private static final AtomicInteger SEQ = new AtomicInteger(100000);
    private List<Inscripcion> inscripciones = new ArrayList<>();
    private List<SolicitudTutoria> solicitudes = new ArrayList<>();

    public Alumno(String nombre, String apellido, String email, ChatMediator mediator) {
        super(nombre, apellido, email, mediator);
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

    public List<Inscripcion> getInscripciones(){return Collections.unmodifiableList(inscripciones);}


    public void enviar(String mensaje) {
        System.out.println(nombre + " envia " + mensaje);
        mediator.enviarMensaje(mensaje, this);
    }
    public void recibir(String mensaje) {
        System.out.println(nombre + " recibe " + mensaje);
    }
}