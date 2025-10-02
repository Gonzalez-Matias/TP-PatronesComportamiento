package org.src.State;

import org.src.entidades.Alumno;
import org.src.entidades.Inscripcion;

public class Cancelado implements EstadoInscripcion{

    @Override
    public void inscribir(Inscripcion inscripcion) {
        System.out.println("El alumno "+ inscripcion.getAlumno().getNombre()+ " se ha aceptado su incripcion\n");
        inscripcion.setEstado(new Inscripto());
    }

    @Override
    public void cancelar(Inscripcion inscripcion) {
        System.out.println("Para el alumno "+ inscripcion.getAlumno().getNombre() + " fue negada su solicitud de inscripcion ");
    }
}
