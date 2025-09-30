package org.src.ChainOfResponsibility;

import org.src.entidades.EstadoSolicitudTutoria;
import org.src.entidades.Materia;
import org.src.entidades.SolicitudTutoria;

public class HandlerCoordinador extends HandlerTutorias {
    @Override
    protected Boolean revisarSolicitud(SolicitudTutoria solicitud) {
        if (solicitud.getEstado() != EstadoSolicitudTutoria.REVISADA_POR_ASISTENTE) {
            return false;
        }

        return comprobarDatos(solicitud);
    }
    private boolean comprobarDatos(SolicitudTutoria solicitud) {
        Materia materia = solicitud.getMateria();
        return materia == Materia.FISICA_I;
    }
}
