package org.src.Mediator;
import org.src.entidades.Persona;

public interface ChatMediator {
    void enviarMensaje(String mensaje, Persona persona);
    void agregarUsuario(Persona persona);
}