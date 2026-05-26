package Estructuras_de_Datos;

import java.io.Serializable;

public class Vertice implements Serializable {
    Object dato;
    Vertice siguiente = null;
    ListaAdyacencia listaAdyacencia = new ListaAdyacencia();

    public Vertice(Object dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
}