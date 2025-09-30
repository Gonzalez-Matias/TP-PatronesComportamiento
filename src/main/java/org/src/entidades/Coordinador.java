package org.src.entidades;

import org.src.Mediator.ChatMediator;

public class Coordinador extends Profesor{
    public Coordinador(String nombre, String apellido, String email, ChatMediator mediator) {
        super(nombre, apellido, email, mediator);
        this.esCoordinador = true;
    }
}
