package org.src.Strategy;

import java.util.List;

public class PromedioSimple implements CalcularNota{

    @Override
    public double calcular(List<Integer> notas) {
        return notas.stream().mapToDouble(Integer:: doubleValue).average().orElse(0.0);
    }
}
