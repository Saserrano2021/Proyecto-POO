package Sistema;

public interface TipoServicios {
    String getNombreServicio();

}

class ServicioEstandar implements TipoServicios {
    @Override
    public String getNombreServicio() {
        return "Servicio Estándar";
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
        return "Servicio Mascostas";
    }
}

class ServicioFactory{
    public static TipoServicios createServicio(String tipo) {
        if(tipo.equalsIgnoreCase("estandar")) {
            return new ServicioEstandar();
        } else if(tipo.equalsIgnoreCase("baul")) {
            return new ServicioBaul();
        } else if(tipo.equalsIgnoreCase("mascotas")) {
            return new ServicioParrilla();
        } else {
            throw new IllegalArgumentException("Tipo de servicio no reconocido: " + tipo);
        }
    }
}
