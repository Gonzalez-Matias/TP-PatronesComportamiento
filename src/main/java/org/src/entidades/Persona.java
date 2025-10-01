package org.src.entidades;

import org.src.Mediator.ChatMediator;
import org.src.Mediator.Usuario;

public abstract class Persona extends Usuario {
    protected String nombre;
    protected String apellido;
    protected String email;
    protected Integer legajo;
    protected String telefono;

    public Persona(String nombre, String apellido, String email, ChatMediator mediator){
        super(mediator, nombre);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.legajo = generarLegajo();
    }

    protected abstract Integer generarLegajo();

    public Integer getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
