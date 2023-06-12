/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

/**
 *
 * @author User
 */
public class Casilla {
    private Fichas ficha;

    public Casilla() {
        this.ficha = null;
    }

    public boolean estaVacia() {
        return ficha == null;
    }

    public Fichas getFicha() {
        return ficha;
    }

    public void setFicha(Fichas ficha) {
        this.ficha = ficha;
    }
}


