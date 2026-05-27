package Sistema;

import java.io.Serializable;

public class Padre implements Serializable {
    private String nombre;
    private String zona;
    private int codigo;

    public Padre(String nombre, String zona, int codigo) {
        this.nombre = nombre;
        this.zona = zona;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getZona() {
        return zona;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }   
}