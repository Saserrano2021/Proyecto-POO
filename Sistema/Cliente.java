package Sistema;

import Estructuras_de_Datos.Pila;

public class Cliente extends Padre{
    private Pila<String> historial;

    public Cliente(String nombre, String zona, int codigo) {
        super(nombre, zona, codigo);
        this.historial = new Pila<>();
    }
    
    public void agregarAlHistorial(String actividad) {
        historial.push(actividad);
    }

    public void mostrarHistorial (){
        if (historial.empty()){
            System.err.println("El cliente" + getNombre() + " no ha realizado ninguna actividad.");
            return;
        }
        System.out.println("Historial de actividades del cliente " + getNombre() + ":");
        historial.print_stack();

    }

    @Override
    public String toString() {
        return "Cliente [getNombre()=" + getNombre() + ", getZona()=" + getZona() + ", getCodigo()=" + getCodigo()
                + "]";
    }

    
}
