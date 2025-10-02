package org.src.Strategy;

import java.util.List;

public class PromedioPonderado implements CalcularNota{

    @Override
    public double calcular(List<Integer> notas) {
        if(notas.size() < 2){
            throw new IllegalArgumentException("Se requiere 2 notas para calcular el promedio ponderado");
        }
        return (notas.get(0) * 0.25)+(notas.get(1) * 0.25);
     }
}
