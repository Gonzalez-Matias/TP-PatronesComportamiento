package org.src.Visitor;

import org.src.Mediator.ChatMediator;
import org.src.entidades.Alumno;

public class AlumnoBecado extends Alumno {
    private boolean tieneBecaTransporte;
    private float descuentoCuota;

    public AlumnoBecado(String nombre, String apellido, String email, ChatMediator mediator, boolean tieneBecaTransporte, float descuentoCuota) {
        super(nombre, apellido, email, mediator);
        this.tieneBecaTransporte = tieneBecaTransporte;
        this.descuentoCuota = descuentoCuota;
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

    public float getDescuentoCuota(){
        return this.descuentoCuota;
    }

    public void setDescuentoCuota(float descuentoCuota){
        this.descuentoCuota = descuentoCuota;
    }
}
