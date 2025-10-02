package org.src.entidades;

import org.src.Iterator.InscripcionIterator;
import org.src.Mediator.ChatMediator;
import org.src.Observer.CursoObserver;
import org.src.State.Cancelado;
import org.src.Strategy.CalcularNota;
import org.src.State.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Alumno extends Persona implements CursoObserver {
    private static final AtomicInteger SEQ = new AtomicInteger(100000);
    private List<Inscripcion> inscripciones = new ArrayList<>();
    private List<SolicitudTutoria> solicitudes = new ArrayList<>();
    private CalcularNota strategy;

    public Alumno(String nombre, String apellido, String email, ChatMediator mediator) {
        super(nombre, apellido, email, mediator);
    }

    @Override
    protected Integer generarLegajo() {
        return SEQ.getAndIncrement();
    }

    public void agregarInscripcion(Inscripcion inscripcion){
        inscripciones.add(inscripcion);
    }

    public void agregarSolicitudTutoria(SolicitudTutoria solicitud){
        solicitudes.add(solicitud);
    }

    public InscripcionIterator inscripcionesIterator(){
        List<Inscripcion> inscripcionesVigentes = new ArrayList<>();
        for (Inscripcion inscripcion : this.inscripciones){
            if (inscripcion.getEstado().getClass() != Cancelado.class){
                inscripcionesVigentes.add(inscripcion);
            }
        }
        return new InscripcionesIterator(inscripcionesVigentes);
    }

    private static class InscripcionesIterator implements InscripcionIterator {
        private List<Inscripcion> inscripciones;
        private int posicion = 0;

        public InscripcionesIterator(List<Inscripcion> inscripciones){
            this.inscripciones = inscripciones;
        }

        @Override
        public boolean hasNext() {
            return posicion < inscripciones.size();
        }

        @Override
        public Inscripcion next() {
            return inscripciones.get(posicion++);
        }
    }


    public void enviar(String mensaje) {
        System.out.println(nombre + " envia " + mensaje);
        mediator.enviarMensaje(mensaje, this);
    }
    public void recibir(String mensaje) {
        System.out.println(nombre + " recibe " + mensaje);
    }

    @Override
    public void update(String msg) {
        System.out.println("Alumno " + nombre + " se notifica: " + msg);
    }

    public void setEstrategiaPromedio(CalcularNota estrategia){
        this.strategy = estrategia;
    }

    public double calcularNota(Materia materia){
        List<Integer> notas = new ArrayList<>();
        for (Inscripcion insc : inscripciones){
            Set<Examen> examenesMateria = insc.getExamenes();
            if (!examenesMateria.isEmpty() && insc.getCurso().getMateria() == materia){
                for (Examen examen : examenesMateria){
                    notas.add(examen.getNota());
                }
            }
        }
        return strategy.calcular(notas);
    }
}