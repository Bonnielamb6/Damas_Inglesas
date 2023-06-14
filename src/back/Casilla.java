/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.awt.Color;

/**
 *
 * @author User
 */
public class Casilla {
    private Color color;
    private Fichas ficha;

    public Casilla(Color color) {
        this.color = color;
        this.ficha = null;
    }

    public Color getColor() {
        return color;
    }

    public boolean estaVacia() {
        return ficha == null;
    }

    public Fichas getFicha() {
        return ficha;
    }

    public void colocarFicha(Fichas ficha) {
        this.ficha = ficha;
    }

    public void quitarFicha() {
        this.ficha = null;
    }
}


