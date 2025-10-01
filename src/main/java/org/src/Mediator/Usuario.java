package org.src.Mediator;

public abstract class Usuario {
    protected ChatMediator mediator;
    protected String nombreUsuario;

    public Usuario(ChatMediator mediator, String nombre){
        this.mediator = mediator;
        this.nombreUsuario = nombre;
    }

    public abstract void enviar(String mensaje);
    public abstract void recibir(String mensaje);
}