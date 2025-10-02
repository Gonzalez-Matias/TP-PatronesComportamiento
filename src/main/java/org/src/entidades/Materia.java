package org.src.entidades;

public enum Materia {
    ALGEBRA("Algebra"),
    ANALISIS_I("Analisis I"),
    FISICA_I("Fisica I"),
    PROGRAMACION_I("Programacion I"),
    BASES_DE_DATOS("Bases de Datos");

    private final String nombre;

    Materia(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }
}
