/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

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
    }

    private void inicializarCasillas() {
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                if ((fila + columna) % 2 == 0) {
                    casillas[fila][columna] = new Casilla(fila, columna, null);
                } else {
                    Color color = (fila < 3) ? Color.WHITE : Color.BLACK;
                    casillas[fila][columna] = new Casilla(fila, columna, new Ficha(fila, columna, color, this));
                }
            }
        }
    }

    public Casilla obtenerCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public void moverFicha(Ficha ficha, int nuevaFila, int nuevaColumna) {
        int filaActual = ficha.getFila();
        int columnaActual = ficha.getColumna();
        Casilla casillaOrigen = casillas[filaActual][columnaActual];
        Casilla casillaDestino = casillas[nuevaFila][nuevaColumna];

        casillaOrigen.removerFicha();
        casillaDestino.setFicha(ficha);
        ficha.setPosicion(nuevaFila, nuevaColumna);

        // Comprobar si la ficha se convierte en reina
        if (!ficha.esReina() && (nuevaFila == 0 || nuevaFila == TAMANO - 1)) {
            ficha.convertirEnReina();
        }
    }
}
