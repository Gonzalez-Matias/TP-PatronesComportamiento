package org.src;


import org.src.ChainOfResponsibility.HandlerAsistente;
import org.src.ChainOfResponsibility.HandlerCoordinador;
import org.src.ChainOfResponsibility.HandlerProfesor;
import org.src.ChainOfResponsibility.HandlerTutorias;
import org.src.Command.*;
import org.src.Mediator.ChatMediator;
import org.src.Mediator.ChatRoom;
import org.src.entidades.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        HandlerTutorias asistente = new HandlerAsistente();
        HandlerTutorias profesor = new HandlerProfesor();
        HandlerTutorias coordinador = new HandlerCoordinador();

        ChatMediator sala1 = new ChatRoom();

        asistente.setNext(profesor);
        profesor.setNext(coordinador);

        Alumno alumno = new Alumno("Ana", "Pérez", "ana.perez@gmail.com", sala1);
        Profesor tutor = new Profesor("Luis", "Gómez", "luis.gomez@hotmail.com", sala1);

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

        Alumno alumno2 = new Alumno("Pedro", "Ricon", "pricon@fakemail.com",sala1 );

        sala1.agregarUsuario(alumno2);
        sala1.agregarUsuario(alumno);
        sala1.agregarUsuario(tutor);

        alumno.enviar("Hola a todos!");
        tutor.enviar("Bienvenida!");


        Curso curso = new Curso(Materia.ANALISIS_I, "3k15", "Noche");
        InscribirseCursoCommand inscribirse = new InscribirseCursoCommand(curso, alumno);
        AbandonarCursoCommand abandonar = new AbandonarCursoCommand(curso, alumno);
        SolicitarCertificadoCommand certificado = new SolicitarCertificadoCommand(curso, alumno);

        CursoCommandInvoker controlCursos = new CursoCommandInvoker();
        controlCursos.setCommand(inscribirse);
        controlCursos.executeCommand();

        controlCursos.setCommand(certificado);
        controlCursos.executeCommand();

        controlCursos.setCommand(abandonar);
        controlCursos.executeCommand();
    }
}