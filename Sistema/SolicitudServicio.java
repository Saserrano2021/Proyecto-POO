package Sistema;

import Estructuras_de_Datos.ColaDinamica;
import Estructuras_de_Datos.Pila;
import java.io.Serializable;
import java.time.LocalDateTime;

public class SolicitudServicio implements Serializable {
    @SuppressWarnings("FieldMayBeFinal")
    private int codigo;
    @SuppressWarnings("FieldMayBeFinal")
    private Cliente cliente;
    private Conductor conductor;
    @SuppressWarnings("FieldMayBeFinal")
    private String origen;
    @SuppressWarnings("FieldMayBeFinal")
    private String destino;
    private String tipoServicio;
    private String estado; //Este atributo dice que si el serivicio esta en espera, se esta atentiendo, esta cancelado o demas estados
    private double tarifa;
    private double tiempoEstimado;
    private Pila<String> historialEstados;
    private ColaDinamica<String> pasosParaEntrega;
    private String motivoDeCancelacion;
    @SuppressWarnings("unused")
    private LocalDateTime fechaSolicitud;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public SolicitudServicio(int codigo, Cliente cliente,String origen, String destino, String tipoServicio) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.origen = origen;
        this.destino = destino;
        this.tipoServicio = tipoServicio;
        this.estado = "En espera"; // Se pone este estado como predeterminado ya que los pedidos siempre empiezan en espera
        this.historialEstados = new Pila<>();
        this.pasosParaEntrega = new ColaDinamica<>();
        this.fechaSolicitud = LocalDateTime.now();

        registrarPaso("La solicitud fue creada por el cliente: " + cliente.getNombre() + " . En el barrio: " + origen + " . Y su destino es: " + destino);
    }

    public SolicitudServicio() {
    }
    
    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public String getEstado() {
        return estado;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public double getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(double tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getMotivoDeCancelacion() {
        return motivoDeCancelacion;
    }

    public void setMotivoDeCancelacion(String motivoDeCancelacion) {
        this.motivoDeCancelacion = motivoDeCancelacion;
    }

    public void registrarPaso(String paso) {
        pasosParaEntrega.enqueue(paso);
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        historialEstados.push("El estado ha sido cambiado a:" + nuevoEstado + " en la fecha: " + LocalDateTime.now());
    }

@Override
    public String toString() {
        return "SolicitudServicio [codigo:" + codigo + ", cliente:" + cliente.getNombre() + ", conductor:" + (conductor != null ? conductor.getNombre() : "No asignado") + ", origen:" + origen + ", destino:" + destino + ", tipoServicio:" + tipoServicio + ", estado:" + estado + ", tarifa:" + tarifa + ", tiempoEstimado:" + tiempoEstimado + "]";
    }

}