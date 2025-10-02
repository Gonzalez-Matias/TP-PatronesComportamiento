package org.src.State;

import org.src.entidades.Inscripcion;

public interface EstadoInscripcion {
    void inscribir (Inscripcion inscripcion);
    void cancelar (Inscripcion inscripcion);
}
