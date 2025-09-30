package org.src.entidades;

import org.src.Mediator.ChatMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Profesor extends Persona {
    private static final AtomicInteger SEQ = new AtomicInteger(10000);
    private List<Curso> cursos = new ArrayList<>();
    private List<Materia> materias = new ArrayList<>();
    protected boolean esCoordinador = false;


    public Profesor(String nombre, String apellido, String email, ChatMediator mediator) {
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
