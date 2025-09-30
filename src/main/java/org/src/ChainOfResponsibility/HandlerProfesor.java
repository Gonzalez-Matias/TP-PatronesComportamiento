package org.src.ChainOfResponsibility;

import org.src.entidades.EstadoSolicitudTutoria;
import org.src.entidades.SolicitudTutoria;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class HandlerProfesor extends HandlerTutorias {

    @Override
    protected Boolean revisarSolicitud(SolicitudTutoria solicitud) {
        if (solicitud.getEstado() != EstadoSolicitudTutoria.REVISADA_POR_ASISTENTE) {
            return false;
        }

        int decision = comprobarDatosProfesor(solicitud);
        return switch (decision) {
            case 0 -> {
                solicitud.setEstado(EstadoSolicitudTutoria.RECHAZADA);
                yield false;
            }
            case 1 -> true;
            case 2 -> {
                solicitud.setEstado(EstadoSolicitudTutoria.REVISADA_POR_PROFESOR);
                yield false;
            }
            default -> false;
        };
    }

    private int comprobarDatosProfesor(SolicitudTutoria solicitud) {
        LocalDateTime inicio = solicitud.getFechaHoraInicioConsulta();
        LocalDateTime fin = solicitud.getFechaHoraFinConsulta();

        long minutos = Duration.between(inicio, fin).toMinutes();
        if (minutos < 30) {
            return 0;
        }

        DayOfWeek dia = inicio.getDayOfWeek();

        if (dia == DayOfWeek.SATURDAY) {
            if (minutos > 90) {
                return 0;
            }
            return 1;
        }

        if (minutos > 240) {
            if (dia == DayOfWeek.TUESDAY || dia == DayOfWeek.THURSDAY) {
                return 1;
            }
            return 2;
        }

        return 1;
    }
}