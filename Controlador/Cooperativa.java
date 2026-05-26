package Controlador;

import Estructuras_de_Datos.Cola;
import Estructuras_de_Datos.Lista;
import Excepciones.*;
import Sistema.Cliente;
import Sistema.Conductor;
import Sistema.Mapa;
import Sistema.Operador;
import Sistema.SolicitudServicio;
import java.io.*;

public class Cooperativa implements Serializable {
    private static Cooperativa instancia;
    private static final String FILE_NAME = "cooperativa_datos.dat";

    @SuppressWarnings("FieldMayBeFinal")
    private Lista<Conductor> conductores;
    @SuppressWarnings("FieldMayBeFinal")
    private Lista<Cliente> clientes;
    @SuppressWarnings("FieldMayBeFinal")
    private Lista<Operador> operadores;
    @SuppressWarnings("FieldMayBeFinal")
    private Cola<SolicitudServicio> solicitudesPendientes;
    @SuppressWarnings("FieldMayBeFinal")
    private Mapa mapa;
    private int contadorSolicitudes;

    private Cooperativa() {
        conductores = new Lista<>();
        clientes = new Lista<>();
        operadores = new Lista<>();
        solicitudesPendientes = new Cola<>();
        mapa = new Mapa();
        contadorSolicitudes = 1;
    }

    public static Cooperativa getInstancia() {
        if (instancia == null) {
            instancia = cargarDatos();
        }
        return instancia;
    }

    // --- Gestión de Entidades ---
    public void registrarConductor(Conductor c) { conductores.insertarFinal(c); }
    public void registrarCliente(Cliente c) { clientes.insertarFinal(c); }
    public void registrarOperador(Operador o) { operadores.insertarFinal(o); }

    public Cliente buscarClientePorCodigo(int codigo) {
        for (int i = 0; i < clientes.tamaño(); i++) {
            Cliente c = clientes.obtenerPorIndice(i);
            if (c.getCodigo() == codigo) return c;
        }
        return null;
    }

    public Operador buscarOperadorPorCodigo(int codigo) {
        for (int i = 0; i < operadores.tamaño(); i++) {
            Operador o = operadores.obtenerPorIndice(i);
            if (o.getCodigo() == codigo) return o;
        }
        return null;
    }

    // --- Lógica de Negocio ---
    public void crearSolicitud(Cliente cliente, String origen, String destino, String tipo) throws Exception {
        double dist = mapa.distancia(origen, destino);
        if (dist == Double.MAX_VALUE) throw new ZonaInexistenteExcepcion.ZonaNoEncontradaException("No hay ruta.");

        SolicitudServicio nueva = new SolicitudServicio(contadorSolicitudes++, cliente, origen, destino, tipo);
        nueva.setTarifa(5000 + (dist * 1000)); // Tarifa base + distancia
        nueva.setTiempoEstimado(dist * 2); // Simulación de tiempo
        solicitudesPendientes.enqueue(nueva);
    }

    public void atenderSolicitud(Operador operador) throws Exception {
        if (solicitudesPendientes.getSize() == 0) return;

        SolicitudServicio solicitud = solicitudesPendientes.dequeue();
        Conductor asignado = null;

        for (int i = 0; i < conductores.tamaño(); i++) {
            Conductor c = conductores.obtenerPorIndice(i);
            if (c.isDisponible() && c.ofrecerServicio(solicitud.getTipoServicio())) {
                asignado = c;
                break;
            }
        }

        if (asignado == null) {
            solicitudesPendientes.enqueue(solicitud); // Re-encolar si no hay nadie
            throw new ConductoNoDisponible.ConductorNoDisponibleException(null);
        }

        solicitud.setConductor(asignado);
        solicitud.cambiarEstado("En camino");
        asignado.setDisponible(false);
        System.out.println("El operador " + operador.getNombre() + " asignó al conductor " + asignado.getNombre());
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    private static Cooperativa cargarDatos() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                return (Cooperativa) ois.readObject();
            } catch (Exception e) {
                System.err.println("Error al cargar, creando nueva cooperativa.");
            }
        }
        return new Cooperativa();
    }

    public Mapa getMapa() { return mapa; }
    public Lista<Conductor> getConductores() { return conductores; }
    public Lista<Cliente> getClientes() { return clientes; }
}