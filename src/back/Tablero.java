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
public class Tablero {
    public static final int TAMANO = 8;
    private Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[TAMANO][TAMANO];
        inicializarCasillas();
        inicializarFichas();
    }

    private void inicializarCasillas() {
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                casillas[fila][columna] = new Casilla();
            }
        }
    }

    private void inicializarFichas() {
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                if ((fila + columna) % 2 == 1 && fila < 3) {
                    casillas[fila][columna].setFicha(new Ficha(fila, columna, Color.GREEN, this));
                } else if ((fila + columna) % 2 == 1 && fila > 4) {
                    casillas[fila][columna].setFicha(new Ficha(fila, columna, Color.RED, this));
                }
            }
        }
    }

    public Casilla obtenerCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public boolean moverFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        Casilla casillaOrigen = casillas[filaOrigen][columnaOrigen];
        Casilla casillaDestino = casillas[filaDestino][columnaDestino];

        if (!casillaOrigen.estaVacia() && casillaDestino.estaVacia()) {
            Ficha ficha = casillaOrigen.getFicha();

            if (ficha.puedeMover(obtenerDireccionMovimiento(filaOrigen, columnaOrigen, filaDestino, columnaDestino))) {
                casillaDestino.setFicha(ficha);
                casillaOrigen.setFicha(null);
                return true;
            }
        }
        return false;
    }

    public boolean hayCapturasDisponibles(Ficha ficha) {
        int fila = ficha.getFila();
        int columna = ficha.getColumna();

        if (ficha.puedeComer(Ficha.Direccion.DIAGONAL_IZQUIERDA_ARRIBA)) {
            int filaCaptura = fila - 1;
            int columnaCaptura = columna - 1;

            if (realizarCaptura(fila, columna, filaCaptura, columnaCaptura)) {
                return true;
            }
        }

        if (ficha.puedeComer(Ficha.Direccion.DIAGONAL_DERECHA_ARRIBA)) {
            int filaCaptura = fila - 1;
            int columnaCaptura = columna + 1;

            if (realizarCaptura(fila, columna, filaCaptura, columnaCaptura)) {
                return true;
            }
        }

        if (ficha.puedeComer(Ficha.Direccion.DIAGONAL_IZQUIERDA_ABAJO)) {
            int filaCaptura = fila + 1;
            int columnaCaptura = columna - 1;

            if (realizarCaptura(fila, columna, filaCaptura, columnaCaptura)) {
                return true;
            }
        }

        if (ficha.puedeComer(Ficha.Direccion.DIAGONAL_DERECHA_ABAJO)) {
            int filaCaptura = fila + 1;
            int columnaCaptura = columna + 1;

            if (realizarCaptura(fila, columna, filaCaptura, columnaCaptura)) {
                return true;
            }
        }

        return false;
    }

    public boolean capturarFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        Casilla casillaOrigen = casillas[filaOrigen][columnaOrigen];
        Casilla casillaDestino = casillas[filaDestino][columnaDestino];

        int filaCaptura = (filaOrigen + filaDestino) / 2;
        int columnaCaptura = (columnaOrigen + columnaDestino) / 2;
        Casilla casillaCaptura = casillas[filaCaptura][columnaCaptura];

        if (!casillaOrigen.estaVacia() && casillaDestino.estaVacia() && !casillaCaptura.estaVacia()) {
            Ficha fichaOrigen = casillaOrigen.getFicha();
            Ficha fichaCaptura = casillaCaptura.getFicha();

            if (fichaOrigen.getColor() != fichaCaptura.getColor()) {
                casillaDestino.setFicha(fichaOrigen);
                casillaOrigen.setFicha(null);
                casillaCaptura.setFicha(null);
                return true;
            }
        }
        return false;
    }
    
    public boolean realizarCaptura(int filaOrigen, int columnaOrigen, int filaCaptura, int columnaCaptura) {
    Casilla casillaOrigen = casillas[filaOrigen][columnaOrigen];
    Casilla casillaCaptura = casillas[filaCaptura][columnaCaptura];
    Casilla casillaDestino = casillas[filaCaptura + (filaCaptura - filaOrigen)][columnaCaptura + (columnaCaptura - columnaOrigen)];

    if (!casillaOrigen.estaVacia() && casillaCaptura.estaVacia() && casillaDestino.estaVacia()) {
        Ficha fichaOrigen = casillaOrigen.getFicha();

        if (fichaOrigen.puedeComer(obtenerDireccionMovimiento(filaOrigen, columnaOrigen, filaCaptura, columnaCaptura))) {
            casillaDestino.setFicha(fichaOrigen);
            casillaOrigen.setFicha(null);
            casillaCaptura.setFicha(null);
            return true;
        }
    }
    return false;
    }


    private Ficha.Direccion obtenerDireccionMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        int incrementoFila = filaDestino - filaOrigen;
        int incrementoColumna = columnaDestino - columnaOrigen;

        if (incrementoFila < 0 && incrementoColumna < 0) {
            return Ficha.Direccion.DIAGONAL_IZQUIERDA_ARRIBA;
        } else if (incrementoFila < 0 && incrementoColumna > 0) {
            return Ficha.Direccion.DIAGONAL_DERECHA_ARRIBA;
        } else if (incrementoFila > 0 && incrementoColumna < 0) {
            return Ficha.Direccion.DIAGONAL_IZQUIERDA_ABAJO;
        } else {
            return Ficha.Direccion.DIAGONAL_DERECHA_ABAJO;
        }
    }
}
