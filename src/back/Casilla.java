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
    private Ficha ficha;

    public Casilla() {
        this.ficha = null;
    }

    public boolean estaVacia() {
        return ficha == null;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
}