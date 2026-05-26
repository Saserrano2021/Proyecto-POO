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

        Nododirigido("El jardin", "La ciudadela", 1.8);
        Nododirigido("La ciudadela", "Ciudad Equidad", 2);
        Nododirigido("Ciudad Equidad", "El mercado", 2.5);
        Nododirigido("El mercado", "La paz", 1.5);

        Nododirigido("Nuevo Milenio", "Nueva Galicia", 1.5);
        Nododirigido("Nueva Galicia", "Pescaito", 2);
        Nododirigido("Pescaito", "Juan23", 1.8);

        Nododirigido("Cartagena", "Taminaka", 3);
        Nododirigido("Taminaka", "Ojeda", 2.5);
        Nododirigido("Ojeda", "Santa Ana", 2);

        Nododirigido("Santa Ana", "El pando", 2);
        Nododirigido("El pando", "11 de Noviembre", 2.5);
        Nododirigido("11 de Noviembre", "El reposo", 2);

        Nododirigido("La paz", "Nuevo Milenio", 3);
        Nododirigido("Juan23", "Cartagena", 3.5);
        Nododirigido("El reposo", "Ciudad Equidad", 4);
        Nododirigido("Galicia", "Nueva Galicia", 2.5);
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