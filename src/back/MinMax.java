/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

/**
 *
 * @author User
 */
public class MinMax {
    private static final int PROFUNDIDAD_MAXIMA = 4; // Profundidad máxima de búsqueda

    public Movimiento obtenerMejorMovimiento(Tablero tablero, Color colorJugador) {
        int mejorValor = Integer.MIN_VALUE;
        Movimiento mejorMovimiento = null;

        for (Movimiento movimiento : generarMovimientosPosibles(tablero, colorJugador)) {
            Tablero tableroCopia = new Tablero(tablero);
            tableroCopia.realizarMovimiento(movimiento);

            int valorMovimiento = minimax(tableroCopia, colorJugador.opuesto(), PROFUNDIDAD_MAXIMA - 1, false);

            if (valorMovimiento > mejorValor) {
                mejorValor = valorMovimiento;
                mejorMovimiento = movimiento;
            }
        }

        return mejorMovimiento;
    }

    private int minimax(Tablero tablero, Color colorJugador, int profundidad, boolean esMaximizador) {
        if (profundidad == 0 || tablero.esFinJuego()) {
            return valoracion(tablero, colorJugador);
        }

        if (esMaximizador) {
            int mejorValor = Integer.MIN_VALUE;

            for (Movimiento movimiento : generarMovimientosPosibles(tablero, colorJugador)) {
                Tablero tableroCopia = new Tablero(tablero);
                tableroCopia.realizarMovimiento(movimiento);

                int valorMovimiento = minimax(tableroCopia, colorJugador.opuesto(), profundidad - 1, false);

                mejorValor = Math.max(mejorValor, valorMovimiento);
            }

            return mejorValor;
        } else {
            int mejorValor = Integer.MAX_VALUE;

            for (Movimiento movimiento : generarMovimientosPosibles(tablero, colorJugador)) {
                Tablero tableroCopia = new Tablero(tablero);
                tableroCopia.realizarMovimiento(movimiento);

                int valorMovimiento = minimax(tableroCopia, colorJugador.opuesto(), profundidad - 1, true);

                mejorValor = Math.min(mejorValor, valorMovimiento);
            }

            return mejorValor;
        }
    }

    private List<Movimiento> generarMovimientosPosibles(Tablero tablero, Color colorJugador) {
        List<Movimiento> movimientosPosibles = new ArrayList<>();

        for (int filaOrigen = 0; filaOrigen < 8; filaOrigen++) {
            for (int columnaOrigen = 0; columnaOrigen < 8; columnaOrigen++) {
                if (tablero.casillaOcupadaPorJugador(filaOrigen, columnaOrigen, colorJugador)) {
                    List<Movimiento> movimientosPieza = generarMovimientosPieza(tablero, filaOrigen, columnaOrigen);
                    movimientosPosibles.addAll(movimientosPieza);
                }
            }
        }

        return movimientosPosibles;
    }

    private List<Movimiento> generarMovimientosPieza(Tablero tablero, int filaOrigen, int columnaOrigen) {
        List<Movimiento> movimientosPieza = new ArrayList<>();
        Pieza pieza = tablero.getPieza(filaOrigen, columnaOrigen);

        // Generar movimientos diagonales hacia adelante
        int[] filasDestino = { filaOrigen - 1, filaOrigen - 1 };
        int[] columnasDestino = { columnaOrigen - 1, columnaOrigen + 1 };

        for (int i = 0; i < filasDestino.length; i++) {
            int filaDestino = filasDestino[i];
            int columnaDestino = columnasDestino[i];

            if (tablero.casillaValida(filaDestino, columnaDestino) && tablero.casillaVacia(filaDestino, columnaDestino)) {
                movimientosPieza.add(new Movimiento(filaOrigen, columnaOrigen, filaDestino, columnaDestino));
            }
        }

        // Generar movimientos de salto (captura de piezas)
        for (int i = 0; i < filasDestino.length; i++) {
            int filaDestino = filasDestino[i];
            int columnaDestino = columnasDestino[i];
            int filaSalto = (filaOrigen + filaDestino) / 2;
            int columnaSalto = (columnaOrigen + columnaDestino) / 2;

            if (tablero.casillaValida(filaDestino, columnaDestino) && tablero.casillaOcupadaPorJugador(filaSalto, columnaSalto, pieza.getColor().opuesto())
                    && tablero.casillaVacia(filaDestino, columnaDestino)) {
                movimientosPieza.add(new Movimiento(filaOrigen, columnaOrigen, filaDestino, columnaDestino));
            }
        }

        return movimientosPieza;
    }

    private int valoracion(Tablero tablero, Color colorJugador) {
        int valorJugador = 0;
        int valorOponente = 0;

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tablero.getPieza(fila, columna);
                if (pieza != null) {
                    int valorPieza = 1; // Valor por defecto para una pieza normal

                    if (pieza.esReina()) {
                        valorPieza = 3; // Valor para una reina
                    }

                    if (pieza.getColor() == colorJugador) {
                        valorJugador += valorPieza;
                    } else {
                        valorOponente += valorPieza;
                    }
                }
            }
        }

        return valorJugador - valorOponente;
    }
}

