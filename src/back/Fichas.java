/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.awt.Color;

/**
 *
 * @author PC
 */
public class Fichas {
    private int fila;
    private int columna;
    private Color color;
    private boolean reina;
    private Tablero tablero;

    public Fichas(int fila, int columna, Color color, Tablero tablero) {
        this.fila = fila;
        this.columna = columna;
        this.color = color;
        this.reina = false;
        this.tablero = tablero;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Color getColor() {
        return color;
    }

    public boolean esReina() {
        return reina;
    }

    public void convertirEnReina() {
        reina = true;
    }

    public boolean puedeMover(Direccion direccion) {
        int incrementoFila = (color == Color.WHITE) ? 1 : -1;

        if (reina) {
            return puedeMoverEnDireccion(fila + incrementoFila, columna - 1, direccion)
                    || puedeMoverEnDireccion(fila + incrementoFila, columna + 1, direccion)
                    || puedeMoverEnDireccion(fila - incrementoFila, columna - 1, direccion)
                    || puedeMoverEnDireccion(fila - incrementoFila, columna + 1, direccion);
        } else {
            return puedeMoverEnDireccion(fila + incrementoFila, columna - 1, direccion)
                    || puedeMoverEnDireccion(fila + incrementoFila, columna + 1, direccion);
        }
    }

    private boolean puedeMoverEnDireccion(int nuevaFila, int nuevaColumna, Direccion direccion) {
        if (nuevaFila >= 0 && nuevaFila < Tablero.TAMANO && nuevaColumna >= 0 && nuevaColumna < Tablero.TAMANO) {
            Casilla casillaDestino = tablero.obtenerCasilla(nuevaFila, nuevaColumna);

            if (casillaDestino.estaVacia()) {
                return true;
            } else if (casillaDestino.getFicha().getColor() != color) {
                // La casilla destino está ocupada por una ficha del color opuesto, verificar si se puede saltar
                int saltoFila = nuevaFila + direccion.incrementoFila;
                int saltoColumna = nuevaColumna + direccion.incrementoColumna;

                if (saltoFila >= 0 && saltoFila < Tablero.TAMANO && saltoColumna >= 0 && saltoColumna < Tablero.TAMANO) {
                    Casilla casillaSalto = tablero.obtenerCasilla(saltoFila, saltoColumna);

                    if (casillaSalto.estaVacia()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean puedeComer(Direccion direccion) {
        int incrementoFila = (color == Color.WHITE) ? 1 : -1;
        int incrementoColumna = (direccion == Direccion.DIAGONAL_IZQUIERDA_ARRIBA || direccion == Direccion.DIAGONAL_IZQUIERDA_ABAJO) ? -1 : 1;

        if (reina) {
            return puedeComerEnDireccion(fila + incrementoFila, columna - 1, incrementoFila, incrementoColumna)
                    || puedeComerEnDireccion(fila + incrementoFila, columna + 1, incrementoFila, incrementoColumna)
                    || puedeComerEnDireccion(fila - incrementoFila, columna - 1, -incrementoFila, incrementoColumna)
                    || puedeComerEnDireccion(fila - incrementoFila, columna + 1, -incrementoFila, incrementoColumna);
        } else {
            return puedeComerEnDireccion(fila + incrementoFila, columna - 1, incrementoFila, incrementoColumna)
                    || puedeComerEnDireccion(fila + incrementoFila, columna + 1, incrementoFila, incrementoColumna);
        }
    }

    private boolean puedeComerEnDireccion(int nuevaFila, int nuevaColumna, int incrementoFila, int incrementoColumna) {
        if (nuevaFila >= 0 && nuevaFila < Tablero.TAMANO && nuevaColumna >= 0 && nuevaColumna < Tablero.TAMANO) {
            Casilla casillaIntermedia = tablero.obtenerCasilla((fila + nuevaFila) / 2, (columna + nuevaColumna) / 2);
            Casilla casillaDestino = tablero.obtenerCasilla(nuevaFila, nuevaColumna);

            if (casillaDestino.estaVacia() && !casillaIntermedia.estaVacia()
                    && casillaIntermedia.getFicha().getColor() != color) {
                return true;
            }
        }
        return false;
    }

    private static class Direccion {
        private int incrementoFila;
        private int incrementoColumna;

        private Direccion(int incrementoFila, int incrementoColumna) {
            this.incrementoFila = incrementoFila;
            this.incrementoColumna = incrementoColumna;
        }

        private static final Direccion DIAGONAL_IZQUIERDA_ARRIBA = new Direccion(-1, -1);
        private static final Direccion DIAGONAL_DERECHA_ARRIBA = new Direccion(-1, 1);
        private static final Direccion DIAGONAL_IZQUIERDA_ABAJO = new Direccion(1, -1);
        private static final Direccion DIAGONAL_DERECHA_ABAJO = new Direccion(1, 1);
    }
}
