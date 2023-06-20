/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class InterfazDamas extends JFrame {
    private static final int TAMANO_TABLERO = 8;
    private static final Color COLOR_CASILLA_CLARA = Color.WHITE;
    private static final Color COLOR_CASILLA_OSCURA = Color.BLACK;
    private static final Color COLOR_FICHA_BLANCA = Color.GREEN;
    private static final Color COLOR_FICHA_NEGRA = Color.RED;

    private JButton[][] casillas;
    private Tablero tablero;
    private JButton casillaSeleccionada;

    public InterfazDamas() {
        tablero = new Tablero();

        setTitle("Damas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelTablero = new JPanel(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
        casillas = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
                JButton casilla = new JButton();
                casilla.addMouseListener(new CasillaListener());
                casilla.setFocusPainted(false);
                casillas[fila][columna] = casilla;
                panelTablero.add(casilla);

                if ((fila + columna) % 2 == 0) {
                    casilla.setBackground(COLOR_CASILLA_CLARA);
                } else {
                    casilla.setBackground(COLOR_CASILLA_OSCURA);
                }
            }
        }

        add(panelTablero);
        setSize(600, 600);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        actualizarTablero();
    }

    private void actualizarTablero() {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
                JButton casilla = casillas[fila][columna];
                Casilla casillaTablero = tablero.obtenerCasilla(fila, columna);
                Ficha ficha = casillaTablero.getFicha();

                if (casillaTablero.estaVacia()) {
                    casilla.setText("");
                    casilla.setEnabled(false);
                } else {
                    casilla.setText(ficha.getColor() == COLOR_FICHA_BLANCA ? "JUGADOR1" : "JUGADOR2");
                    casilla.setEnabled(true);
                    casilla.setForeground(ficha.getColor());
                }
            }
        }
    }

    private void moverFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        Casilla casillaOrigen = tablero.obtenerCasilla(filaOrigen, columnaOrigen);
        Casilla casillaDestino = tablero.obtenerCasilla(filaDestino, columnaDestino);

        if (!casillaOrigen.estaVacia() && casillaDestino.estaVacia()) {
            Ficha ficha = casillaOrigen.getFicha();

            if (ficha.getColor() == COLOR_FICHA_BLANCA && ficha.esMovimientoValido(filaDestino, columnaDestino)) {
                casillaDestino.setFicha(ficha);
                casillaOrigen.setFicha(null);
                actualizarTablero();
            } else {
                JOptionPane.showMessageDialog(this, "Movimiento inválido");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Casilla de destino ocupada");
        }
    }

    private class CasillaListener extends MouseAdapter {
        private int filaOrigen;
        private int columnaOrigen;

        @Override
        public void mousePressed(MouseEvent e) {
            casillaSeleccionada = (JButton) e.getSource();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton casillaDestino = (JButton) e.getSource();
            int filaDestino = -1;
            int columnaDestino = -1;

            // Encontrar la casilla destino
            for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
                for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
                    if (casillas[fila][columna] == casillaDestino) {
                        filaDestino = fila;
                        columnaDestino = columna;
                        break;
                    }
                }
            }

            if (casillaSeleccionada != null && filaDestino != -1 && columnaDestino != -1) {
                int filaOrigen = -1;
                int columnaOrigen = -1;

                // Encontrar la casilla origen
                for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
                    for (int columna = 0; columna < TAMANO_TABLERO; columna++) {
                        if (casillas[fila][columna] == casillaSeleccionada) {
                            filaOrigen = fila;
                            columnaOrigen = columna;
                            break;
                        }
                    }
                }

                if (filaOrigen != -1 && columnaOrigen != -1) {
                    moverFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
                }
            }

            casillaSeleccionada = null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazDamas();
            }
        });
    }
}
