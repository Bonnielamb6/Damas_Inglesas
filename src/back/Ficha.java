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
public class Ficha {
    private int fila;
    private int columna;
    private Color color;
    private boolean reina;
    private Tablero tablero;
    private static final Color COLOR_FICHA_BLANCA = Color.GREEN;
    private static final Color COLOR_FICHA_NEGRA = Color.RED;
    private static final int TAMANO_TABLERO = 8;

    public Ficha(int fila, int columna, Color color, Tablero tablero) {
        this.fila = fila;
        this.columna = columna;
        this.color = color;
        this.reina = false;
        this.tablero = tablero;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean esReina() {
        return reina;
    }

    public void setReina(boolean reina) {
        this.reina = reina;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean puedeMover(Direccion direccion) {
        int incrementoFila = (color == Color.GREEN) ? 1 : -1;

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
    
    public boolean esMovimientoValido(int filaDestino, int columnaDestino) {
        int direccion = (color == COLOR_FICHA_BLANCA) ? -1 : 1;

        int difFila = filaDestino - fila;
        int difColumna = columnaDestino - columna;

        // Verificar si el movimiento es diagonal hacia adelante
        if (Math.abs(difFila) == 1 && Math.abs(difColumna) == 1) {
            // Verificar dirección y casilla de destino vacía
            if (difFila == direccion && casillaDestinoVacia(filaDestino, columnaDestino)) {
                return true;
            }
        }

        return false;
    }

    private boolean casillaDestinoVacia(int filaDestino, int columnaDestino) {
        Casilla casilla = tablero.obtenerCasilla(filaDestino, columnaDestino);
        return casilla.estaVacia();
    }

    private boolean puedeMoverEnDireccion(int nuevaFila, int nuevaColumna, Direccion direccion) {
        if (nuevaFila >= 0 && nuevaFila < TAMANO_TABLERO && nuevaColumna >= 0 && nuevaColumna < TAMANO_TABLERO) {
            Casilla casillaDestino = tablero.obtenerCasilla(nuevaFila, nuevaColumna);

            if (casillaDestino.estaVacia()) {
                return true;
            } else if (casillaDestino.getFicha().getColor() != color) {
                // La casilla destino está ocupada por una ficha del color opuesto, verificar si se puede saltar
                int saltoFila = nuevaFila + direccion.incrementoFila;
                int saltoColumna = nuevaColumna + direccion.incrementoColumna;

                if (saltoFila >= 0 && saltoFila < TAMANO_TABLERO && saltoColumna >= 0 && saltoColumna < TAMANO_TABLERO) {
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
        int incrementoFila = (color == Color.GREEN) ? 1 : -1;
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
        if (nuevaFila >= 0 && nuevaFila < TAMANO_TABLERO && nuevaColumna >= 0 && nuevaColumna < TAMANO_TABLERO) {
            Casilla casillaIntermedia = tablero.obtenerCasilla((fila + nuevaFila) / 2, (columna + nuevaColumna) / 2);
            Casilla casillaDestino = tablero.obtenerCasilla(nuevaFila, nuevaColumna);

            if (casillaDestino.estaVacia() && !casillaIntermedia.estaVacia()
                    && casillaIntermedia.getFicha().getColor() != color) {
                return true;
            }
        }
        return false;
    }

    public static class Direccion {
        private int incrementoFila;
        private int incrementoColumna;

        private Direccion(int incrementoFila, int incrementoColumna) {
            this.incrementoFila = incrementoFila;
            this.incrementoColumna = incrementoColumna;
        }

        public static final Direccion DIAGONAL_IZQUIERDA_ARRIBA = new Direccion(-1, -1);
        public static final Direccion DIAGONAL_DERECHA_ARRIBA = new Direccion(-1, 1);
        public static final Direccion DIAGONAL_IZQUIERDA_ABAJO = new Direccion(1, -1);
        public static final Direccion DIAGONAL_DERECHA_ABAJO = new Direccion(1, 1);
    }
}
