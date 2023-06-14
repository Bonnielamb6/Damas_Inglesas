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

public class InterfazDamas extends JFrame {
    private Tablero tablero;

    public InterfazDamas() {
        tablero = new Tablero();

        setTitle("Damas Inglesas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        JPanel panelTablero = new JPanel(new GridLayout(Tablero.TAMANO, Tablero.TAMANO));
        panelTablero.setPreferredSize(new Dimension(500, 500));
        panelTablero.setBackground(Color.WHITE);

        // Crear las casillas del tablero y agregarlas al panel
        for (int fila = 0; fila < Tablero.TAMANO; fila++) {
            for (int columna = 0; columna < Tablero.TAMANO; columna++) {
                Casilla casilla = tablero.obtenerCasilla(fila, columna);
                JButton botonCasilla = new JButton();
                botonCasilla.setPreferredSize(new Dimension(60, 60));
                botonCasilla.setEnabled(false);
                botonCasilla.setBackground(casilla.getColor());
                if (casilla.estaOcupada()) {
                    Fichas ficha = casilla.getFicha();
                    botonCasilla.setIcon(ficha.getIcon());
                }
                panelTablero.add(botonCasilla);
            }
        }

        add(panelTablero);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazDamas());
    }
}