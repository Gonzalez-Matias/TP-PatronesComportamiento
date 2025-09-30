package org.src.entidades;

import org.src.Mediator.ChatMediator;

import java.util.concurrent.atomic.AtomicInteger;

public class Asistente extends Persona{
    private static final AtomicInteger SEQ = new AtomicInteger(1000);


    public Asistente(String nombre, String apellido, String email, ChatMediator mediator) {
        super(nombre, apellido, email, mediator);
    }

    @Override
    protected Integer generarLegajo() {
        return SEQ.getAndIncrement();
    }

    public void enviar(String mensaje) {
        System.out.println(nombre + " envia " + mensaje);
        mediator.enviarMensaje(mensaje, this);
    }
    public void recibir(String mensaje) {
        System.out.println(nombre + " recibe " + mensaje);
    }
}
