package org.src.Visitor;

import org.src.Mediator.ChatMediator;
import org.src.entidades.Alumno;

public class AlumnoRegular extends Alumno {
    private boolean tieneBecaTransporte;

    public AlumnoRegular(String nombre, String apellido, String email, ChatMediator mediator, boolean tieneBecaTransporte) {
        super(nombre, apellido, email, mediator);
        this.tieneBecaTransporte = tieneBecaTransporte;
    }
    public void aceptar(Visitor v){
        v.visitar(this);
    }

    public boolean getTieneBecaTransporte(){
        return this.tieneBecaTransporte;
    }

    public void setTieneBecaTransporte(boolean tieneBecaTransporte){
        this.tieneBecaTransporte = tieneBecaTransporte;
    }
}
