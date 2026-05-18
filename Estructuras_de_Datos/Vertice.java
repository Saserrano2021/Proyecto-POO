package Estructuras_de_Datos;

public class Vertice {
    Object dato;
    Vertice siguiente = null;
    ListaAdyacencia listaAdyacencia = new ListaAdyacencia();

    public Vertice(Object dato) {
        this.dato = dato;
    }

    public String toString() {
        return dato.toString();
    }
}
