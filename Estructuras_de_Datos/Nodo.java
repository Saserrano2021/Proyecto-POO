package Estructuras_de_Datos;

public class Nodo<T> {
    T dato;
    Nodo<T> siguiente;
    Nodo<T> anterior;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}
