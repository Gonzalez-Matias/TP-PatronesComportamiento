package org.src;


import org.src.ChainOfResponsibility.HandlerAsistente;
import org.src.ChainOfResponsibility.HandlerCoordinador;
import org.src.ChainOfResponsibility.HandlerProfesor;
import org.src.ChainOfResponsibility.HandlerTutorias;
import org.src.entidades.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        HandlerTutorias asistente = new HandlerAsistente();
        HandlerTutorias profesor = new HandlerProfesor();
        HandlerTutorias coordinador = new HandlerCoordinador();

        asistente.setNext(profesor);
        profesor.setNext(coordinador);

        Alumno alumno = new Alumno("Ana", "Pérez", "ana.perez@gmail.com");
        Profesor tutor = new Profesor("Luis", "Gómez", "luis.gomez@hotmail.com");

        // Debe ser rechazada por ser un domingo
        SolicitudTutoria s1 = new SolicitudTutoria(
                alumno,
                tutor,
                Materia.FISICA_I,
                LocalDateTime.of(2025, 9, 28, 10, 0),  // DOMINGO
                LocalDateTime.of(2025, 9, 28, 12, 0)
        );
        asistente.handle(s1);

        // Debe ser rechazada por ser de más de 4 hs y ser día miércoles.
        SolicitudTutoria s2 = new SolicitudTutoria(
                alumno,
                tutor,
                Materia.ALGEBRA,
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 15, 30)
        );
        asistente.handle(s2);

        // Debe ser aprobada
        SolicitudTutoria s3 = new SolicitudTutoria(
                alumno,
                tutor,
                Materia.FISICA_I,
                LocalDateTime.of(2025, 9, 30, 10, 0),
                LocalDateTime.of(2025, 9, 30, 15, 30)
        );
        asistente.handle(s3);
    }
}