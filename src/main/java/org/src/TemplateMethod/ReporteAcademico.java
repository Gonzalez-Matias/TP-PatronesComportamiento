package org.src.TemplateMethod;

public abstract class ReporteAcademico {

    public final void  generarReporte (){
        encabezado();
        contenido();
        pie ();
    }

    protected void encabezado (){
        System.out.println("*** INFORME ACADEMICO ***\n");
        System.out.println("Fecha: " + java.time.LocalDate.now());
        System.out.println("----------------------------------");
    }

    protected abstract void contenido ();

    protected void pie (){
        System.out.println("-----------------------------");
        System.out.println("Fin del informe.");
    }
}
