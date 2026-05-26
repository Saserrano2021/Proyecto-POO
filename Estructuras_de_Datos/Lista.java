package Estructuras_de_Datos;

import java.io.Serializable;

// Lista doblemente enlazada
public class Lista<T> implements Serializable {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamano;

    public int tamano() {
        return tamano;
    }

    public boolean Isempty() {
        return frente == null;
    }

    public T obtenerPorIndice(int indice){
        if(frente == null || indice < 0 || indice >= tamano){
            return null;
        }
        Nodo<T> temp = frente;
        int contador = 0;
        while(temp != null && contador < indice){
            temp = temp.siguiente;
            contador++;
        }
        return (temp != null) ? temp.dato : null;
    }

    // Recorrer de frente a fin
    public void recorrerFrenteAFin() {
        if (frente == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.siguiente;
        }
        System.out.println();
    }

    // Recorrer de fin a frente
    public void recorrerFinAFrente() {
        if (fin == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Nodo<T> temp = fin;
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.anterior;
        }
        System.out.println();
    }

    // Insertar al inicio
    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (frente == null) {
            frente = fin = nuevo;
        } else {
            nuevo.siguiente = frente;
            frente.anterior = nuevo;
            frente = nuevo;
        }
        tamano++;
    }

    // Insertar al final
    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (frente == null) {
            frente = fin = nuevo;
        } else {
            nuevo.anterior = fin;
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamano++;
    }

    // Insertar despues de un nodo
    public void insertarDespues(T referencia, T dato) {
        if (frente == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(referencia)) {
            temp = temp.siguiente;
        }
        if (temp == null) {
            System.out.println("Elemento no encontrado");
            return;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = temp.siguiente;
        nuevo.anterior = temp;
        if (temp.siguiente != null) {
            temp.siguiente.anterior = nuevo;
        } else {
            fin = nuevo;
        }
        temp.siguiente = nuevo;
        tamano++;
    }

    // Insertar antes de un nodo
    public void insertarAntes(T referencia, T dato) {
        if (frente == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(referencia)) {
            temp = temp.siguiente;
        }
        if (temp == null) {
            System.out.println("Elemento no encontrado");
            return;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = temp;
        nuevo.anterior = temp.anterior;
        if (temp.anterior != null) {
            temp.anterior.siguiente = nuevo;
        } else {
            frente = nuevo;
        }
        temp.anterior = nuevo;
        tamano++;
    }

    // Buscar un elemento
    public boolean buscar(T valor) {
        Nodo<T> temp = frente;
        while (temp != null) {
            if (temp.dato.equals(valor)) {
                System.out.println("Elemento encontrado: " + temp.dato);
                return true;
            }
            temp = temp.siguiente;
        }
        System.out.println("Elemento no encontrado.");
        return false;
    }

    // Borrar primero
    public void borrarPrimero() {
        if (frente == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        if (frente == fin) {
            frente = fin = null;
        } else {
            frente = frente.siguiente;
            frente.anterior = null;
        }
        tamano--;
    }

    // Borrar ultimo
    public void borrarUltimo() {
        if (fin == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        if (frente == fin) {
            frente = fin = null;
        } else {
            fin = fin.anterior;
            fin.siguiente = null;
        }
        tamano--;
    }

    // Borrar despues de un nodo
    public void borrarDespues(T referencia) {
        if (frente == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(referencia)) {
            temp = temp.siguiente;
        }
        if (temp == null || temp.siguiente == null) {
            System.out.println("No hay elemento despues de " + referencia);
            return;
        }
        Nodo<T> borrar = temp.siguiente;
        temp.siguiente = borrar.siguiente;
        if (borrar.siguiente != null) {
            borrar.siguiente.anterior = temp;
        } else {
            fin = temp;
        }
        tamano--;
    }

    // Borrar antes de un nodo
    public void borrarAntes(T referencia) {
        if (frente == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(referencia)) {
            temp = temp.siguiente;
        }
        if (temp == null || temp.anterior == null) {
            System.out.println("No hay elemento antes de " + referencia);
            return;
        }
        Nodo<T> borrar = temp.anterior;
        temp.anterior = borrar.anterior;
        if (borrar.anterior != null) {
            borrar.anterior.siguiente = temp;
        } else {
            frente = temp;
        }
        tamano--;
    }

    // Insertar en una posicion especifica
    public void insertarEnPosicion(T dato, int posicion) {
        if (posicion < 0 || posicion > tamano) {
            System.out.println("Posicion invalida");
            return;
        }
        if (posicion == 0) {
            insertarInicio(dato);
            return;
        }
        if (posicion == tamano) {
            insertarFinal(dato);
            return;
        }
        Nodo<T> temp = frente;
        for (int i = 0; i < posicion; i++) {
            temp = temp.siguiente;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.anterior = temp.anterior;
        nuevo.siguiente = temp;
        temp.anterior.siguiente = nuevo;
        temp.anterior = nuevo;
        tamano++;
    }

    // Borrar por valor (normal)
    public void borrar(T valor) {
        if (frente == null) {
            System.out.println("La lista esta vacia");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(valor)) {
            temp = temp.siguiente;
        }
        if (temp == null) {
            System.out.println("Elemento no encontrado");
            return;
        }
        if (temp == frente) {
            borrarPrimero();
        } else if (temp == fin) {
            borrarUltimo();
        } else {
            temp.anterior.siguiente = temp.siguiente;
            temp.siguiente.anterior = temp.anterior;
        }
        tamano--;
    }
}