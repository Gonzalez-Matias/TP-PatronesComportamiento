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
import org.src.Strategy.PromedioPonderado;
import org.src.Strategy.PromedioSimple;
import org.src.TemplateMethod.ReporteAcademico;
import org.src.TemplateMethod.ReporteAlumno;
import org.src.TemplateMethod.ReporteCurso;
import org.src.Visitor.AlumnoBecado;
import org.src.Visitor.AlumnoRegular;
import org.src.Visitor.AplicarBeca;
import org.src.entidades.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Banner general
        System.out.println("\n====================================================");
        System.out.println("===== SISTEMA DE GESTION HOSPITALARIA (DEMO) =======");
        System.out.println("====================================================\n");

        // =====================================================================
        // ====================== MEDIATOR — PRUEBAS ============================
        // =====================================================================
        System.out.println("[MEDIATOR]  Creacion de sala y registro de usuarios");
        ChatMediator sala1 = new ChatRoom();

        AlumnoBecado alumno = new AlumnoBecado("Ana", "Perez", "ana.perez@gmail.com", sala1, true, 0.8F);
        Profesor tutor = new Profesor("Luis", "Gomez", "luis.gomez@hotmail.com", sala1);
        Alumno alumno2 = new AlumnoRegular("Pedro", "Ricon", "pricon@fakemail.com", sala1, false);

        sala1.agregarUsuario(alumno2);
        sala1.agregarUsuario(alumno);
        sala1.agregarUsuario(tutor);

        System.out.println("[MEDIATOR]  Envio de mensajes");
        alumno.enviar("Hola a todos!");
        tutor.enviar("Bienvenida!");

        // =====================================================================
        // ======================= COMMAND — PRUEBAS ============================
        // =====================================================================
        System.out.println("\n----------------------------------------------------");
        System.out.println("[COMMAND]  Creacion de cursos y comandos");
        Curso curso = new Curso(Materia.ANALISIS_I, "3k15", "Noche");
        Curso curso2 = new Curso(Materia.ALGEBRA, "1k01", "Mañana");
        Curso curso3 = new Curso(Materia.FISICA_I, "2k07", "Tarde");

        InscribirseCursoCommand inscribirse = new InscribirseCursoCommand(curso, alumno);
        InscribirseCursoCommand inscribirse2 = new InscribirseCursoCommand(curso2, alumno);
        InscribirseCursoCommand inscribirse3 = new InscribirseCursoCommand(curso3, alumno);
        AbandonarCursoCommand abandonar = new AbandonarCursoCommand(curso, alumno);
        SolicitarCertificadoCommand certificado = new SolicitarCertificadoCommand(curso, alumno);

        CursoCommandInvoker controlCursos = new CursoCommandInvoker();
        System.out.println("[COMMAND]  Ejecutando Inscribirse (x4)");
        controlCursos.setCommand(inscribirse);  controlCursos.executeCommand();
        controlCursos.setCommand(inscribirse2); controlCursos.executeCommand();
        controlCursos.setCommand(inscribirse3); controlCursos.executeCommand();

        System.out.println("[COMMAND]  Ejecutando SolicitarCertificado");
        controlCursos.setCommand(certificado);  controlCursos.executeCommand();

        System.out.println("[COMMAND]  Ejecutando Abandonar curso");
        controlCursos.setCommand(abandonar);    controlCursos.executeCommand();

        // =====================================================================
        // ======================== ITERATOR — PRUEBAS ==========================
        // =====================================================================
        System.out.println("\n----------------------------------------------------");
        System.out.println("[ITERATOR]  Listado de inscripciones del alumno");
        InscripcionIterator inscripciones = alumno.inscripcionesIterator();
        System.out.println("El alumno " + alumno.getLegajo() + " esta inscrito en:");
        while (inscripciones.hasNext()){
            System.out.println("  - " + inscripciones.next().getCurso().getMateria());
        }

        // =====================================================================
        // ======================== OBSERVER — PRUEBAS ==========================
        // =====================================================================
        System.out.println("\n----------------------------------------------------");
        System.out.println("[OBSERVER]  Notificacion por cambio de profesor/aviso");
        Profesor profesor2 = new Profesor("Ricardo", "Centurion", "prueba@test.com", sala1);

        curso.addObserver(alumno);
        curso.addObserver(alumno2);
        curso.setProfesor(profesor2);
        System.out.println("[OBSERVER]  Enviando aviso al curso");
        curso.nuevoAviso("Se suspende la clase de consulta del 20/10 por viento zonda");

        // =====================================================================
        // ======================== MEMENTO — PRUEBAS ===========================
        // =====================================================================
        System.out.println("\n----------------------------------------------------");
        System.out.println("[MEMENTO]  Guardar y restaurar estado de examen");
        Curso curso4 = new Curso(Materia.PROGRAMACION_I, "3k22", "Noche");
        Inscripcion inscripcion1 = new Inscripcion(alumno, curso4);
        Examen examen1 = new Examen(inscripcion1,6, LocalDateTime.now(),1);
        Examen examen2 = new Examen(inscripcion1,10, LocalDateTime.now(),2);
        Examen examen3 = new Examen(inscripcion1,10, LocalDateTime.now(),3);
        Memento m1 = examen1.save();
        Memento m2 = examen2.save();
        System.out.println("[MEMENTO] Estado inicial  nota = " + examen1.getNota());
        examen1.setNota(9);
        System.out.println("[MEMENTO] Estado modificado  nota = " + examen1.getNota());
        examen1.restore(m1);
        System.out.println("[MEMENTO] Estado restaurado  nota = " + examen1.getNota());

        // =====================================================================
        // ========================= VISITOR — PRUEBAS ==========================
        // =====================================================================
        System.out.println("\n----------------------------------------------------");
        System.out.println("[VISITOR]  Aplicacion de beca al alumno");
        AplicarBeca becas = new AplicarBeca();
        becas.visitar(alumno);

        // =====================================================================
        // ================= CHAIN OF RESPONSIBILITY — PRUEBAS =================
        // =====================================================================
        System.out.println("\n----------------------------------------------------");
        System.out.println("[CHAIN OF RESPONSIBILITY]  Validacion de solicitudes de tutoria");
        HandlerTutorias asistente = new HandlerAsistente();
        HandlerTutorias profesor = new HandlerProfesor();
        HandlerTutorias coordinador = new HandlerCoordinador();

        asistente.setNext(profesor);
        profesor.setNext(coordinador);

        System.out.println("[CHAIN]  Creando solicitudes (s1, s2, s3)");
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

        System.out.println("[CHAIN]  Procesando s1 (debe ser rechazada por domingo)");
        asistente.handle(s1);
        System.out.println("[CHAIN]  Procesando s2 (rechazo por > 4 hs en dia habil)");
        asistente.handle(s2);
        System.out.println("[CHAIN]  Procesando s3 (debe ser aprobada)");
        asistente.handle(s3);

        // =====================================================================
        // ========================= STATE — PRUEBAS ===========================
        // =====================================================================
        System.out.println("\n----------------------------------------------------");
        System.out.println("[STATE]  Probando inscripciones con cambios de estado");

        Curso cursoState1 = new Curso(Materia.ANALISIS_I, "3k10", "Noche");
        Curso cursoState2 = new Curso(Materia.FISICA_I, "3k09", "Tarde");

        // Inscripcion de alumno en curso
        Inscripcion inscrip1 = new Inscripcion(alumno, cursoState1);

        System.out.println("[STATE]  Aceptando inscripcion");
        inscrip1.aceptarAlumno();

        System.out.println("[STATE]  Rechazando inscripcion");
        inscrip1.rechazarAlumno();

        System.out.println("[STATE]  Volviendo a aceptar inscripcion");
        inscrip1.aceptarAlumno();

        // =====================================================================
        // ========================= STRATEGY — PRUEBAS ===========================
        // =====================================================================

        System.out.println("[STRATEGY] Calculando promedio ponderardo de " + alumno.getNombre() + " en la materia Programacion 1");
        alumno.setEstrategiaPromedio(new PromedioPonderado());
        double prom = alumno.calcularNota(Materia.PROGRAMACION_I);
        System.out.println(prom);

        System.out.println("[STRATEGY] Calculando promedio simple de " + alumno.getNombre() + " en la materia Programacion 1");
        alumno.setEstrategiaPromedio(new PromedioSimple());
        double proSimple = alumno.calcularNota(Materia.PROGRAMACION_I);
        System.out.println(proSimple);

        // =====================================================================
        // ========================= TEMPLATEMETHOD — PRUEBAS ===========================
        // =====================================================================
        System.out.println("\nGenerando informe del curso:");
        ReporteAcademico informeCurso = new ReporteCurso(curso2);
        informeCurso.generarReporte();

        // Generar informe de un alumno
        System.out.println("\nGenerando informe del alumno:");
        ReporteAcademico informeAlumno = new ReporteAlumno(alumno, curso4);
        informeAlumno.generarReporte();

        System.out.println("\n====================================================");
        System.out.println("=============== FIN DE LA DEMOSTRACION =============");
        System.out.println("====================================================\n");
    }
}
