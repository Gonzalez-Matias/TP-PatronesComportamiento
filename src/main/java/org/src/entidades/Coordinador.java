package org.src.entidades;

public class Coordinador extends Profesor{
    public Coordinador(String nombre, String apellido, String email) {
        super(nombre, apellido, email);
        this.esCoordinador = true;
    }
}
