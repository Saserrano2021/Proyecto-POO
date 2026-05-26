package Estructuras_de_Datos;

import java.io.Serializable;

public class Nodo<T> implements Serializable {
    T dato;
    Nodo<T> siguiente;
    Nodo<T> anterior;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}