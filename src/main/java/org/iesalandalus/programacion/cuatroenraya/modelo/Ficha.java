package org.iesalandalus.programacion.cuatroenraya.modelo;

public enum Ficha {
    VERDE("V"),
    AZUL("A");

    private String cadenaAMostrar;

    private Ficha(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }

}
