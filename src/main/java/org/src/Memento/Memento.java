package org.src.Memento;

import java.time.LocalDateTime;

public class Memento {
    public final float nota;
    public final LocalDateTime fechaHoraExamen;
    public final int numExamen;

    public Memento(float nota, LocalDateTime fechaHoraExamen, int numExamen) {
        this.nota = nota;
        this.fechaHoraExamen = fechaHoraExamen;
        this.numExamen = numExamen;
    }
}
