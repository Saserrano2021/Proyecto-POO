package Sistema;

import Estructuras_de_Datos.Lista;
import Excepciones.ConductorExcepciones;
import Excepciones.ConductorExcepciones.ConductorNoEncontradoException;

public class Conductor extends Padre {
    private String placa;
    private String id;
    private boolean disponible;
    private Lista<String> tiposdeServicio;

    public Conductor(String nombre, String zona, int codigo, String placa, String id) throws ConductorNoEncontradoException {
        super(nombre, zona, codigo);
        if (placa == null || placa.trim().isEmpty()) {
            throw new ConductorExcepciones.ConductorNoEncontradoException("La placa del conductor no puede estar vacía.");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new ConductorExcepciones.ConductorNoEncontradoException("La identificación (ID) no puede estar vacía.");
        }
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
