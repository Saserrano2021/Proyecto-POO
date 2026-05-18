package Sistema;

import Estructuras_de_Datos.Lista;

public class Conductor extends Padre {
    private String placa;
    private String id;
    private boolean disponible;
    private Lista<String> tiposdeServicio;

    public Conductor(String nombre, String zona, int codigo, String placa, String id) {
        super(nombre, zona, codigo);
        this.placa = placa;
        this.id = id;
        this.disponible = true; // Por defecto, el conductor está disponible
        this.tiposdeServicio = new Lista<>();
    }

    public String getPlaca() {
        return placa;
    }

    public String getId() {
        return id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void habilitarServicio(String tipoServicio){
        if(!tiposdeServicio.buscar(tipoServicio)){
            tiposdeServicio.insertarFinal(tipoServicio);
        }
    }

    public boolean ofreceServicio(String tipoServicio){
        return tiposdeServicio.buscar(tipoServicio);
    }

    @Override
    public String toString() {
        return "Conductor [getNombre()=" + getNombre() + ", getZona()=" + getZona() + ", getCodigo()=" + getCodigo()
                + ", getPlaca()=" + getPlaca() + ", isDisponible()=" + isDisponible() + "]";
    }

    
}
