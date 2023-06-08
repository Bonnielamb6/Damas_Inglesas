/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

/**
 *
 * @author PC
 */
public class Fichas {
    private int fila;                   //Fila en la que se encuentra nuestra ficha
    private int columna;                //Columna en la que se encuentra nuestra ficha
    private int estado;                 //Estado actual de nuestra ficha
    private int boba;                   //Va a eliminarse la ficha por boba o no? (si puede comer pero no come entonces se elimina por boba)
    private int comerDerecha;           //Puede comer hacia su derecha al frente?
    private int comerIzquierda;         //Puede comer hacia su izquierda al frente?
    private int comerDerechaAbajo;      //Puede comer hacia abajo a la derecha?(Solo se puede si antes ya habia comido una ficha o si comio una hacia al frente)
    private int comerIzquierdaAbajo;    //Puede comer hacia abajo a la izquierda?(Solo se puede si antes ya habia comido una ficha o si comio una hacia al frente)
    private int moverDerecha;           //Se puede mover hacia la derecha? (Hay un espacio vacio hacia donde moverse?)
    private int moverIzquierda;         //Se puede mover hacia la izquierda?(Hay un espacio vacio hacia donde moverse?)
    private int color;                  //Color de la ficha (0 -> Negra, 1 -> Blanca)
    
    public Fichas(int fila, int columna, int estado, int boba, int comerDerecha, int comerIzquierda, int comerDerechaAbajo, int comerIzquierdaAbajo,int moverDerecha, int moverIzquierda,int color) {
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
        this.boba = boba;
        this.comerDerecha = comerDerecha;
        this.comerIzquierda = comerIzquierda;
        this.comerDerechaAbajo = comerDerechaAbajo;
        this.comerIzquierdaAbajo = comerIzquierdaAbajo;
        this.moverDerecha = moverDerecha;
        this.moverIzquierda = moverIzquierda;
        this.color = color;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getBoba() {
        return boba;
    }

    public void setBoba(int boba) {
        this.boba = boba;
    }

    public int getComerDerecha() {
        return comerDerecha;
    }

    public void setComerDerecha(int comerDerecha) {
        this.comerDerecha = comerDerecha;
    }

    public int getComerIzquierda() {
        return comerIzquierda;
    }

    public void setComerIzquierda(int comerIzquierda) {
        this.comerIzquierda = comerIzquierda;
    }

    public int getComerDerechaAbajo() {
        return comerDerechaAbajo;
    }

    public void setComerDerechaAbajo(int comerDerechaAbajo) {
        this.comerDerechaAbajo = comerDerechaAbajo;
    }

    public int getComerIzquierdaAbajo() {
        return comerIzquierdaAbajo;
    }

    public void setComerIzquierdaAbajo(int comerIzquierdaAbajo) {
        this.comerIzquierdaAbajo = comerIzquierdaAbajo;
    }

    public int getMoverDerecha() {
        return moverDerecha;
    }

    public void setMoverDerecha(int moverDerecha) {
        this.moverDerecha = moverDerecha;
    }

    public int getMoverIzquierda() {
        return moverIzquierda;
    }

    public void setMoverIzquierda(int moverIzquierda) {
        this.moverIzquierda = moverIzquierda;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    
    
}
