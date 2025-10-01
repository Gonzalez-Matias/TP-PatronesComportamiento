package org.src;

import org.src.ChainOfResponsibility.HandlerAsistente;
import org.src.ChainOfResponsibility.HandlerCoordinador;
import org.src.ChainOfResponsibility.HandlerProfesor;
import org.src.ChainOfResponsibility.HandlerTutorias;
import org.src.Command.*;
import org.src.Iterator.InscripcionIterator;
import org.src.Mediator.ChatMediator;
import org.src.Mediator.ChatRoom;
import org.src.Memento.Memento;
import org.src.Visitor.AlumnoBecado;
import org.src.Visitor.AlumnoRegular;
import org.src.Visitor.AplicarBeca;
import org.src.entidades.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== SISTEMA DE GESTIÓN HOSPITALARIA =====\n");

        // =====================================================================
        // ====================== MEDIATOR — PRUEBAS ============================
        // =====================================================================
        // Se crea una sala de chat (mediator), se agregan usuarios y se intercambian mensajes.
        ChatMediator sala1 = new ChatRoom();

        AlumnoBecado alumno = new AlumnoBecado("Ana", "Pérez", "ana.perez@gmail.com", sala1, true, 0.8F);
        Profesor tutor = new Profesor("Luis", "Gómez", "luis.gomez@hotmail.com", sala1);
        Alumno alumno2 = new AlumnoRegular("Pedro", "Ricon", "pricon@fakemail.com", sala1, false);

        sala1.agregarUsuario(alumno2);
        sala1.agregarUsuario(alumno);
        sala1.agregarUsuario(tutor);

        alumno.enviar("Hola a todos!");
        tutor.enviar("Bienvenida!");

        // =====================================================================
        // ======================= COMMAND — PRUEBAS ============================
        // =====================================================================
        // Se crean cursos y comandos. Se inscribe al alumno en varios cursos,
        // se solicita certificado y luego se abandona un curso.
        Curso curso = new Curso(Materia.ANALISIS_I, "3k15", "Noche");
        Curso curso2 = new Curso(Materia.ALGEBRA, "1k01", "Mañana");
        Curso curso3 = new Curso(Materia.FISICA_I, "2k07", "Tarde");
        Curso curso4 = new Curso(Materia.PROGRAMACION_I, "3k22", "Noche");

        InscribirseCursoCommand inscribirse = new InscribirseCursoCommand(curso, alumno);
        InscribirseCursoCommand inscribirse2 = new InscribirseCursoCommand(curso2, alumno);
        InscribirseCursoCommand inscribirse3 = new InscribirseCursoCommand(curso3, alumno);
        InscribirseCursoCommand inscribirse4 = new InscribirseCursoCommand(curso4, alumno);
        AbandonarCursoCommand abandonar = new AbandonarCursoCommand(curso, alumno);
        SolicitarCertificadoCommand certificado = new SolicitarCertificadoCommand(curso, alumno);

        CursoCommandInvoker controlCursos = new CursoCommandInvoker();
        controlCursos.setCommand(inscribirse);
        controlCursos.executeCommand();
        controlCursos.setCommand(inscribirse2);
        controlCursos.executeCommand();
        controlCursos.setCommand(inscribirse3);
        controlCursos.executeCommand();
        controlCursos.setCommand(inscribirse4);
        controlCursos.executeCommand();

        controlCursos.setCommand(certificado);
        controlCursos.executeCommand();

        controlCursos.setCommand(abandonar);
        controlCursos.executeCommand();

        // =====================================================================
        // ======================== ITERATOR — PRUEBAS ==========================
        // =====================================================================
        // Se recorre la lista de inscripciones del alumno mediante un iterador.
        InscripcionIterator inscripciones = alumno.inscripcionesIterator();
        System.out.println("El alumno " + alumno.getLegajo() + " está inscrito en:");
        while (inscripciones.hasNext()){
            System.out.println("- " + inscripciones.next().getCurso().getMateria());
        }

        // =====================================================================
        // ======================== OBSERVER — PRUEBAS ==========================
        // =====================================================================
        // Se asigna un nuevo profesor al curso y se notifica a los alumnos.
        Profesor profesor2 = new Profesor("Ricardo", "Centurion", "prueba@test.com", sala1);

        curso.addObserver(alumno);
        curso.addObserver(alumno2);
        curso.setProfesor(profesor2);
        curso.nuevoAviso("Se suspende la clase de consulta del 20/10 por viento zonda");

        // =====================================================================
        // ======================== MEMENTO — PRUEBAS ===========================
        // =====================================================================
        // Se inicia un examen, se guarda su estado (memento), se modifica y se restaura.
        Inscripcion inscripcion1 = new Inscripcion(alumno, curso);
        Examen examen1 = new Examen(inscripcion1,6.0f, LocalDateTime.now(),1);
        Memento m1 = examen1.save();
        System.out.println("Estado inicial → nota= " + examen1.getNota());
        examen1.setNota(9.0f);
        System.out.println("Estado post modificacion de notas → nota= " + examen1.getNota());
        examen1.restore(m1);
        System.out.println("Estado final luego de restarurar examen → nota= " + examen1.getNota());

        // =====================================================================
        // ========================= VISITOR — PRUEBAS ==========================
        // =====================================================================
        // Se aplica la lógica de becas visitando al alumno.
        AplicarBeca becas = new AplicarBeca();
        becas.visitar(alumno);

        // =====================================================================
        // ================= CHAIN OF RESPONSIBILITY — PRUEBAS =================
        // =====================================================================
        // Se encadenan los handlers y se prueban reglas de validación en solicitudes:
        // - s1 debe ser rechazada por ser en domingo.
        // - s2 debe ser rechazada por duración > 4 hs en día hábil.
        // - s3 debe ser aprobada al cumplir las restricciones.
        HandlerTutorias asistente = new HandlerAsistente();
        HandlerTutorias profesor = new HandlerProfesor();
        HandlerTutorias coordinador = new HandlerCoordinador();

        asistente.setNext(profesor);
        profesor.setNext(coordinador);

        SolicitudTutoria s1 = new SolicitudTutoria(
                alumno,
                tutor,
                Materia.FISICA_I,
                LocalDateTime.of(2025, 9, 28, 10, 0),  // DOMINGO
                LocalDateTime.of(2025, 9, 28, 12, 0)
        );

        SolicitudTutoria s2 = new SolicitudTutoria(
                alumno,
                tutor,
                Materia.ALGEBRA,
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 15, 30)
        );

        SolicitudTutoria s3 = new SolicitudTutoria(
                alumno,
                tutor,
                Materia.FISICA_I,
                LocalDateTime.of(2025, 9, 30, 10, 0),
                LocalDateTime.of(2025, 9, 30, 15, 30)
        );

        asistente.handle(s1);
        asistente.handle(s2);
        asistente.handle(s3);
    }
}
