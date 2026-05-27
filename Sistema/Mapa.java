package Sistema;

import Estructuras_de_Datos.Grafo;
import Estructuras_de_Datos.Vertice;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Mapa implements Serializable {
    @SuppressWarnings("FieldMayBeFinal")
    
    private Grafo grafo;

    public static String[] Barrios = {
            "Centro Historico", "Bastidas", "Galicia", "El prado", "El jardin", "La ciudadela",
            "Ciudad Equidad", "El mercado", "La paz", "Nuevo Milenio", "Nueva Galicia",
            "Pescaito", "Juan23","Cartagena","Taminaka", "Ojeda","Santa Ana","El pando",
            "11 de Noviembre", "El reposo"
    };

    public Mapa(){
        grafo = new Grafo();
        inicializarMapa();

    }
    private void Nododirigido(String origen, String destino, double peso){
        grafo.agregarArista(origen, destino, peso);
        grafo.agregarArista(destino, origen, peso);
    }

    private void inicializarMapa(){

        for (String barrio : Barrios){
            grafo.agregarVertice(barrio);
        }
        Nododirigido("Centro Historico", "Bastidas", 2);
        Nododirigido("Centro Historico", "Galicia", 3);
        Nododirigido("Centro Historico", "El prado", 3);
        Nododirigido("Centro Historico", "El jardin", 3);
        Nododirigido("Centro Historico", "La ciudadela", 3);
        Nododirigido("Centro Historico", "Ciudad Equidad", 3);
        Nododirigido("Centro Historico", "El mercado", 3);
        Nododirigido("Centro Historico", "La paz", 3);
        Nododirigido("Centro Historico", "Nuevo Milenio", 3);
        Nododirigido("Centro Historico", "Nueva Galicia", 3);
        Nododirigido("Centro Historico", "Pescaito", 3);
        Nododirigido("Centro Historico", "Juan23", 3);
        Nododirigido("Centro Historico", "Cartagena", 3);
        Nododirigido("Centro Historico", "Taminaka", 3);
        Nododirigido("Centro Historico", "Ojeda", 3);
        Nododirigido("Centro Historico", "Santa Ana", 3);
        Nododirigido("Centro Historico", "El pando", 3);
        Nododirigido("Centro Historico", "11 de Noviembre", 3);
        Nododirigido("Centro Historico", "El reposo", 3);

        Nododirigido("Bastidas", "Galicia", 2);
        Nododirigido("Bastidas", "El prado", 2.5);
        Nododirigido("Bastidas", "Taminaka", 4);

        Nododirigido("El prado", "El jardin", 1.5);
        Nododirigido("El prado", "La ciudadela", 2);
        Nododirigido("El prado", "Galicia", 2.5);

       Nododirigido("Centro Histórico", "Pescaito", 1.5);
    Nododirigido("Pescaito", "Juan23", 1.2);
    Nododirigido("Juan23", "Cartagena", 1.8);
    Nododirigido("Cartagena", "Taminaka", 1.5);
    Nododirigido("Taminaka", "Bastidas", 2.0);
    Nododirigido("Bastidas", "Centro Histórico", 2.2);

    Nododirigido("Centro Histórico", "El mercado", 1.0);
    Nododirigido("El mercado", "El prado", 1.3);
    Nododirigido("El prado", "El jardin", 1.1);
    Nododirigido("El jardin", "La ciudadela", 1.4);
    Nododirigido("La ciudadela", "Taminaka", 1.9);

    Nododirigido("Bastidas", "Galicia", 2.5);
    Nododirigido("Galicia", "Nueva Galicia", 1.2);
    Nododirigido("Nueva Galicia", "Nuevo Milenio", 1.6);
    Nododirigido("Nuevo Milenio", "La paz", 1.8);
    Nododirigido("La paz", "Ciudad Equidad", 2.1);

    Nododirigido("Ciudad Equidad", "El reposo", 2.5);
    Nododirigido("El reposo", "11 de Noviembre", 1.3);
    Nododirigido("11 de Noviembre", "El pando", 2.0);
    Nododirigido("El pando", "Ojeda", 1.4);
    Nododirigido("Ojeda", "Santa Ana", 1.1);
    Nododirigido("Santa Ana", "Ciudad Equidad", 2.8);

    Nododirigido("La ciudadela", "El pando", 3.0);
    Nododirigido("El mercado", "Galicia", 3.5);
    Nododirigido("El jardin", "Ciudad Equidad", 4.2);
    Nododirigido("Juan23", "Nueva Galicia", 3.8);
    }


    public double distancia(String origen, String destino){
        HashMap<Vertice, Double> distancias = grafo.Dijkstra(origen);
        if(distancias == null) return Double.MAX_VALUE;

        for(Map.Entry<Vertice, Double> entry : distancias.entrySet()){
            if(entry.getKey().toString().equals(destino)){
                return entry.getValue();
            }
        }
        return Double.MAX_VALUE;
    }


    public void habilitarConexion(String a, String b, double peso){
        Nododirigido(a, b, peso);
    }

    public void quitarConexion(String a, String b){
        grafo.eliminarArista(a, b);
        grafo.eliminarArista(b, a);
    }
}