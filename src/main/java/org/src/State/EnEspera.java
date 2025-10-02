package org.src.State;

import org.src.entidades.Alumno;
import org.src.entidades.Inscripcion;

public class EnEspera implements EstadoInscripcion{



    @Override
    public void inscribir(Inscripcion inscripcion) {
        System.out.println("El alumno "+ inscripcion.getAlumno().getNombre() + " , se ha inscripto");
        inscripcion.setEstado(new Inscripto());
    }

    @Override
    public void cancelar(Inscripcion inscripcion) {
        System.out.println("Se ha cancelado la inscripcion para el estudiante "+ inscripcion.getAlumno().getNombre());
        inscripcion.setEstado(new Cancelado());
    }
}
