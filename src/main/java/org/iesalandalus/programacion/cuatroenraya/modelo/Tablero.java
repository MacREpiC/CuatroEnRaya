package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Tablero {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] arrayCasilla = new Casilla[FILAS][COLUMNAS];

    public Tablero() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                arrayCasilla[fila][columna] = null;
            }
        }
    }

    public boolean estaVacio() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (arrayCasilla[fila][columna] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean columnaVacia(int columna) {
        for (int fila = 0; fila < FILAS; fila++) {
            if (arrayCasilla[fila][1] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean estaLleno() {
        for (int columna = 0; columna < COLUMNAS; columna++) {
            if (!columnaLlena(columna)) {
                return false;
            }
        }
        return true;
    }

    private boolean columnaLlena(int columna) {
        for (int fila = 0; fila < FILAS; fila++) {
            if (arrayCasilla[fila][7] == null) {
                return false;
            }
        }
        return true;
    }
    public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
        Objects.requireNonNull(ficha, "La ficha no puede ser nula.");
        Objects.requireNonNull(columna, "La columna no puede ser nula.");
        if(columna < 0 || columna > COLUMNAS) {
            throw new IllegalArgumentException("La columna debe estar entre 0 y " + (COLUMNAS - 1));
        }

        if(columnaLlena(columna)) {
            throw new OperationNotSupportedException("La columna está llena.");
        }
        return false;
    }
    private void comprobarFicha(Ficha ficha){
        Objects.requireNonNull(ficha, "La ficha no puede ser nula.");
    }
}
