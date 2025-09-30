package org.src.ChainOfResponsibility;

import org.src.entidades.EstadoSolicitudTutoria;
import org.src.entidades.SolicitudTutoria;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class HandlerAsistente extends HandlerTutorias {

    @Override
    protected Boolean revisarSolicitud(SolicitudTutoria solicitud) {
        if (solicitud.getEstado() != EstadoSolicitudTutoria.ENVIADA) {
            return false;
        }

        return switch (comprobarDatosSolicitud(solicitud)) {
            case 0 -> {
                solicitud.setEstado(EstadoSolicitudTutoria.RECHAZADA);
                yield false;
            }
            case 1 -> true;
            case 2 -> {
                solicitud.setEstado(EstadoSolicitudTutoria.REVISADA_POR_ASISTENTE);
                yield false;
            }
            default -> false;
        };
    }

    private int comprobarDatosSolicitud(SolicitudTutoria solicitud){
        LocalDateTime inicio = solicitud.getFechaHoraInicioConsulta();
        LocalDateTime fin = solicitud.getFechaHoraFinConsulta();
        long minutosTotales = Duration.between(inicio, fin).toMinutes();
        DayOfWeek diaSemana = solicitud.getFechaHoraInicioConsulta().getDayOfWeek();

        int hIni = inicio.getHour();
        int hFin = fin.getHour();
        if (!(hIni >= 8 && hFin <= 20)) {
            return 0;
        }

        if (minutosTotales > 300 || diaSemana.compareTo(DayOfWeek.SATURDAY) == 0){
            return 2;
        } else if (diaSemana.compareTo(DayOfWeek.SUNDAY) == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
