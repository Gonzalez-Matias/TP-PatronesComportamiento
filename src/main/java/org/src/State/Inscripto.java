package org.src.State;

import org.src.entidades.Alumno;
import org.src.entidades.Inscripcion;

public class Inscripto implements EstadoInscripcion{

    @Override
    public void inscribir(Inscripcion inscripcion) {
        System.out.println("El alumno: " + inscripcion.getAlumno().getNombre() + " ya se encuentra inscripto");
    }

    @Override
    public void cancelar(Inscripcion inscripcion) {
        System.out.println("El alumno "+ inscripcion.getAlumno().getNombre() + " se le rechazo la inscripcion  ");
        inscripcion.setEstado(new Cancelado());
    }
}
