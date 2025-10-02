package org.src.ChainOfResponsibility;

import org.src.entidades.EstadoSolicitudTutoria;
import org.src.entidades.SolicitudTutoria;

public abstract class HandlerTutorias {
    private HandlerTutorias next;

    public final void setNext(HandlerTutorias next){
        this.next = next;
    };


    public void handle(SolicitudTutoria solicitud){
        if (!revisarSolicitud(solicitud) && this.next != null && solicitud.getEstado() != EstadoSolicitudTutoria.RECHAZADA){
            next.handle(solicitud);
        } else if (revisarSolicitud(solicitud)) {
            solicitud.setEstado(EstadoSolicitudTutoria.APROBADA);
            System.out.println("La solicitud fue aprobada por " + this.getClass().getName());
        } else {
            solicitud.setEstado(EstadoSolicitudTutoria.RECHAZADA);
            System.out.println("La solicitud fue rechazada por " + this.getClass().getName());
        }
    };

    protected abstract Boolean revisarSolicitud(SolicitudTutoria solicitud);
}
