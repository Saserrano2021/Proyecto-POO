package Sistema;

import java.io.Serializable;

public class Operador extends Padre implements Serializable {
    private String turno;
    public Operador(String nombre, String zona, int codigo, String turno) {
        super(nombre, zona, codigo);
        this.turno = turno;
    }

  public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    
    @Override
    public String toString(){
        return "Operador: " + "Nombre: " + getNombre() + ", Zona: " + getZona() + ", Codigo: " + getCodigo() + ", Turno: " + getTurno();
    }

}