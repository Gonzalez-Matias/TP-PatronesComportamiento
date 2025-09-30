package org.src.entidades;

public enum Materia {
    ALGEBRA("Algebra"),
    ANALISIS_I("Análisis I"),
    FISICA_I("Física I"),
    PROGRAMACION_I("Programación I"),
    BASES_DE_DATOS("Bases de Datos");

    private final String nombre;

    Materia(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }
}
