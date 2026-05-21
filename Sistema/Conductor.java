package Sistema;

import Estructuras_de_Datos.Lista;
import Excepciones.ConductorExcepciones;
import Excepciones.ConductorExcepciones.ConductorNoEncontradoException;
import Excepciones.ServicioExcepcion.ServicioNoEncontradoException;

public class Conductor extends Padre {
    private String placa;
    private String id;
    private boolean disponible;
    private Lista<TipoServicios> tiposdeServicio;

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
        this.disponible = true; 
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

    public boolean ofrecerServicio(String tipoServicio){
        for (int i = 0; i < tiposdeServicio.tamaño(); i++) {
            TipoServicios servicioGuardado = tiposdeServicio.obtenerPorIndice(i);
            if(servicioGuardado != null ){
                if(servicioGuardado.getNombreServicio().toLowerCase().contains(tipoServicio.toLowerCase())){
                    return true; 
                }
            }
        }
        return false;
    }

    public void hablitarServicio(String tipoServicio) throws ServicioNoEncontradoException{
       if(!ofrecerServicio(tipoServicio)){
            TipoServicios nuevoServicio = ServicioFactory.createServicio(tipoServicio);
            tiposdeServicio.insertarInicio(nuevoServicio);
        } 
    }

    @Override
    public String toString() {
        return "Conductor [getNombre()=" + getNombre() + ", getZona()=" + getZona() + ", getCodigo()=" + getCodigo()
                + ", getPlaca()=" + getPlaca() + ", isDisponible()=" + isDisponible() + "]";
    }

    
}
