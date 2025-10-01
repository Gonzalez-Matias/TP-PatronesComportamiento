package org.src.Visitor;

public class AplicarBeca implements Visitor {
    private final float cuota = 120000.0F;

    @Override
    public void visitar(AlumnoRegular a) {
        if (a.getTieneBecaTransporte()){
            System.out.println("El alumno " + a.getLegajo()
                    + " debe pagar un total de " + cuota
                    + " y posee beca de transporte.");
            return;
        }
        System.out.println("El alumno " + a.getLegajo()
                + " debe pagar un total de " + cuota
                + " y no posee ninguna beca activa.");
    }

    @Override
    public void visitar(AlumnoBecado a) {
        float descuento = 100 - a.getDescuentoCuota()*100;
        float valorFinal = this.cuota*a.getDescuentoCuota();
        if (a.getTieneBecaTransporte()){
            System.out.println("El alumno " + a.getLegajo()
                    + " tiene un descuento en su cuota del " + descuento
                    + "% por lo que debe pagar un total de " + valorFinal
                    + " y posee beca de transporte.");
            return;
        }
        System.out.println("El alumno " + a.getLegajo()
                + " tiene un descuento en su cuota del " + descuento
                + "% por lo que debe pagar un total de " + valorFinal
                + " y no posee ninguna beca activa.");
    }
}
