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

    static int TAMANO;  
    private int filas;                  //numero de filas que tiene nuestro tablero
    private int columnas;               //numero de columnas que tiene nuestro tablero
    private int estado;                 //estado de nuestro tablero
    private int cantidadBlancas;        //Cantidad de fichas blancas que tiene nuestro tablero
    private int cantidadNegras;         //Cantidad de fichas negras que tiene nuestro tablero
    private Casilla[][] casillas;

    
    
    public Tablero(int filas, int columnas, int estado, int cantidadBlancas, int cantidadNegras) {
        this.filas = filas;
        
        this.columnas = columnas;
        this.estado = estado;
        this.cantidadBlancas = cantidadBlancas;
        this.cantidadNegras = cantidadNegras;
    }

    public Casilla obtenerCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }
    
    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCantidadBlancas() {
        return cantidadBlancas;
    }

    public void setCantidadBlancas(int cantidadBlancas) {
        this.cantidadBlancas = cantidadBlancas;
    }

    public int getCantidadNegras() {
        return cantidadNegras;
    }

    public void setCantidadNegras(int cantidadNegras) {
        this.cantidadNegras = cantidadNegras;
    }
    
    
    
}
        