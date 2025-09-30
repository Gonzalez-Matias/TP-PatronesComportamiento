package org.src.Mediator;

import org.src.entidades.Persona;

public class ChatRoom implements ChatMediator {

    private java.util.List<Persona> personas = new java.util.ArrayList<>();

    public void agregarUsuario(Persona persona){
        personas.add(persona);
        System.out.println(persona.getNombre() + " " + persona.getApellido() + " ha ingresado a la sala.");
    }

    public void enviarMensaje(String mensaje, Persona persona){
        for (Persona p : personas) {
            if (p != persona) {
                p.recibir(mensaje);
            }
        }
    }
}