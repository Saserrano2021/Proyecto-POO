package Sistema;

import Excepciones.ServicioExcepcion;
import Excepciones.ServicioExcepcion.ServicioNoEncontradoException;
import java.io.Serializable;

public interface TipoServicios extends Serializable {
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

class ServicioFactory implements Serializable {
    public static TipoServicios createServicio(String tipo) throws ServicioNoEncontradoException {
        if(tipo.equalsIgnoreCase("Estandar")) {
            return new ServicioEstandar();
        } else if(tipo.equalsIgnoreCase("Baul")) {
            return new ServicioBaul();
        } else if(tipo.equalsIgnoreCase("Mascotas")) {
            return new ServicioMascotas();
        } else if(tipo.equalsIgnoreCase("Parrilla")) {
            return new ServicioParrilla();
        } else {
            throw new ServicioExcepcion.ServicioNoEncontradoException("El tipo de servicio '" + tipo + "' no es un tipo de servicio valido.");
        }
    }
}