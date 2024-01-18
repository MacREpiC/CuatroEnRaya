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
        return (!arrayCasilla[FILAS - 1][columna].estaOcupada());
    }

    public boolean estaLleno() {
        boolean lleno = true;
        for (int i = 0; i < COLUMNAS && lleno; i++) {
            lleno = columnaLlena(i);
        }
        return lleno;
    }

    private boolean columnaLlena(int columna) {
        return arrayCasilla[0][columna].estaOcupada();
    }

    public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
        comprobarColumna(columna);
        comprobarFicha(ficha);
    }

    private void comprobarFicha(Ficha ficha) {
        Objects.requireNonNull(ficha, "La ficha no puede ser nula.");
    }

    public void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("La columna pasada por parámetro es incorrecta: " + columna);
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        boolean vacia = false;
        int filaVacia = 0;
        for (int i = 0; i < FILAS && vacia == false; i++) {
            if (!arrayCasilla[i][columna].estaOcupada()) {
                filaVacia = i;
                vacia = true;
            }
        }
        return filaVacia;
    }

    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        boolean alcanzado = true;
        if (fichasIgualesConsecutivas > FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
            alcanzado = false;
        }
        return alcanzado;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        boolean ganador = false;
        int numColoresConsecA = 0;
        int numColoresConsecV = 0;
        for (int i = 0; i < FILAS || ganador == true; i++) {
            Casilla casillaActual = arrayCasilla[fila][COLUMNAS];
            if (casillaActual != null && casillaActual.getFicha() == Ficha.AZUL) {
                numColoresConsecA++;
                numColoresConsecV = 0;
            } else if (casillaActual != null && casillaActual.getFicha() == Ficha.VERDE) {
                numColoresConsecV++;
                numColoresConsecA = 0;
            }
            if (numColoresConsecA >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS || numColoresConsecV >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                ganador = true;
            }

        }
        return ganador;
    }
}