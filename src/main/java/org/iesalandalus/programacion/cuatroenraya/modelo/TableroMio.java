package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class TableroMio {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] arrayCasilla;

    public TableroMio() {
        arrayCasilla = new Casilla[FILAS][COLUMNAS];
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                arrayCasilla[fila][columna] = new Casilla();
            }
        }
    }

    public boolean estaVacio() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (arrayCasilla[fila][columna].estaOcupada()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean columnaVacia(int columna) {
        return arrayCasilla[FILAS - 1][columna].getFicha() == null;
    }

    public boolean columnaLlena(int columna) {
        return arrayCasilla[0][columna].estaOcupada();
    }

    public boolean estaLleno() {
        boolean lleno = true;
        for (int i = 0; i < COLUMNAS && lleno; i++) {
            lleno = columnaLlena(i);
        }
        return lleno;
    }

    private void comprobarFicha(Ficha ficha) {
        Objects.requireNonNull(ficha, "La ficha no puede ser nula.");
    }

    public void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta.");
        }
    }

    private int getPrimeraFilaVacia(int columna) throws OperationNotSupportedException {
        if (columnaLlena(columna)){
            throw new OperationNotSupportedException("Columna llena.");
        }
        int nFila = -1 ;
        for (int i = FILAS - 1; i >= 0  && nFila == -1; i--){
            if (arrayCasilla[i][columna].getFicha() == null){
                nFila = i;
            }
        }
        return nFila;
    }

    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int numColoresConsecA = 0;
        int numColoresConsecV = 0;
        for (int columna = 0; columna < COLUMNAS; columna++) {
            Casilla casillaActual = arrayCasilla[fila][columna];
            if (casillaActual != null && casillaActual.getFicha().equals(Ficha.AZUL)) {
                numColoresConsecA++;
                numColoresConsecV = 0;
            } else if (casillaActual != null && casillaActual.getFicha().equals(Ficha.VERDE)) {
                numColoresConsecV++;
                numColoresConsecA = 0;
            }
            if (numColoresConsecA >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS || numColoresConsecV >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                return true;
            }
        }
        return false;
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        int numColoresConsecA = 0;
        int numColoresConsecV = 0;
        for (int fila = 0; fila < FILAS; fila++) {
            Casilla casillaActual = arrayCasilla[fila][columna];
            if (casillaActual != null && casillaActual.getFicha() == Ficha.AZUL) {
                numColoresConsecA++;
                numColoresConsecV = 0;
            } else if (casillaActual != null && casillaActual.getFicha() == Ficha.VERDE) {
                numColoresConsecV++;
                numColoresConsecA = 0;
            }
            if (numColoresConsecA >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS || numColoresConsecV >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                return true;
            }
        }
        return false;
    }

    private int menor(int a, int b) {
        return Math.min(a, b);
    }

    private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
        int desplazamiento = menor(fila, columna);
        int filaInicial = fila - desplazamiento;
        int columnaInicial = columna - desplazamiento;
        int numColoresConsec = 0;
        for (int i = 0; i < FILAS && i < COLUMNAS; i++) {
            Casilla casillaActual = arrayCasilla[filaInicial + i][columnaInicial + i];
            if (casillaActual != null && casillaActual.getFicha() == Ficha.AZUL) {
                numColoresConsec++;
            } else if (casillaActual != null && casillaActual.getFicha() == Ficha.VERDE) {
                numColoresConsec++;
            } else {
                numColoresConsec = 0;
            }
            if (numColoresConsec >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                return true;
            }
        }
        return false;
    }

    private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
        int desplazamiento = menor(fila, COLUMNAS - 1 - columna);
        int filaInicial = fila - desplazamiento;
        int columnaInicial = columna + desplazamiento;
        int numColoresConsec = 0;
        for (int i = 0; i < FILAS && i < COLUMNAS; i++) {
            Casilla casillaActual = arrayCasilla[filaInicial + i][columnaInicial - i];
            if (casillaActual != null && casillaActual.getFicha() == Ficha.AZUL) {
                numColoresConsec++;
            } else if (casillaActual != null && casillaActual.getFicha() == Ficha.VERDE) {
                numColoresConsec++;
            } else {
                numColoresConsec = 0;
            }
            if (numColoresConsec >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                return true;
            }
        }
        return false;
    }

    private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
        return comprobarHorizontal(fila, ficha) || comprobarVertical(columna, ficha) ||
                comprobarDiagonalNE(fila, columna, ficha) || comprobarDiagonalNO(fila, columna, ficha);
    }

    public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
        comprobarFicha(ficha);
        comprobarColumna(columna);
        if (estaLleno()) {
            throw new OperationNotSupportedException("No se pueden introducir más fichas porque el tablero está lleno.");
        }
        int fila = getPrimeraFilaVacia(columna);
        arrayCasilla[fila][columna].setFicha(ficha);
        return comprobarTirada(fila, columna, ficha);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                stringBuilder.append(arrayCasilla[fila][columna].toString()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}