package org.src.Strategy;

import java.util.List;

public class ExamenExtra implements CalcularNota{

    @Override
    public double calcular(List<Integer> notas) {
        if (notas.isEmpty()){
            return 0.0;
        }
        double promedio = new PromedioSimple().calcular(notas.subList(0,notas.size() - 1));
        int notaExamen = notas.get(notas.size() - 1);
        return Math.max(promedio,notaExamen);
    }
}
