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
        return (!arrayCasilla[FILAS-1][columna].estaOcupada());
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
    private void comprobarFicha(Ficha ficha){
        Objects.requireNonNull(ficha, "La ficha no puede ser nula.");
    }
    public void comprobarColumna(int columna){
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("La columna pasada por parámetro es incorrecta: " + columna);
        }
    }
    private int getPrimeraFilaVacia(int columna){
        boolean vacia = false;
        int filaVacia = 0;
        for(int i = 0; i < FILAS && vacia == false;i++){
            if(!arrayCasilla[i][columna].estaOcupada()){
                filaVacia = i;
                vacia = true;
            }
        }
        return filaVacia;
    }
}
