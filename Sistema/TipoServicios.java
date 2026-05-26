package Sistema;

import Excepciones.ServicioExcepcion;
import Excepciones.ServicioExcepcion.ServicioNoEncontradoException;

public interface TipoServicios {
    String getNombreServicio();

}

class ServicioEstandar implements TipoServicios {
    @Override
    public String getNombreServicio() {
        return "Servicio Estandar";
    }
}

class ServicioBaul implements TipoServicios {
    @Override
    public String getNombreServicio() {
        return "Servicio Baul";
    }
}
class ServicioParrilla implements TipoServicios {
    @Override
    public String getNombreServicio() {
        return "Servicio Parrilla";
    }
}

class ServicioMascotas implements TipoServicios {
    @Override
    public String getNombreServicio() {
        return "Servicio Mascotas";
    }
}

class ServicioFactory{
    public static TipoServicios createServicio(String tipo) throws ServicioNoEncontradoException {
        if(tipo.equalsIgnoreCase("estandar")) {
            return new ServicioEstandar();
        } else if(tipo.equalsIgnoreCase("baul")) {
            return new ServicioBaul();
        } else if(tipo.equalsIgnoreCase("mascotas")) {
            return new ServicioMascotas();
        } else if(tipo.equalsIgnoreCase("parrilla")) {
            return new ServicioParrilla();
        } else {
            throw new ServicioExcepcion.ServicioNoEncontradoException("El tipo de servicio '" + tipo + "' no es un tipo de servicio valido.");
        }
    }
}