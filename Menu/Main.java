
import Controlador.Cooperativa;
import Sistema.Cliente;
import Sistema.Conductor;
import Sistema.Mapa;
import Sistema.Operador;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("FieldMayBeFinal")
    private static Cooperativa cooperativa = Cooperativa.getInstancia();
    @SuppressWarnings("FieldMayBeFinal")
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE TAXIS - MENU PRINCIPAL ===");
            System.out.println("1. Modo Gestion (Administrador/Operador)");
            System.out.println("2. Modo Cliente");
            System.out.println("0. Salir y Guardar");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuGestion();
                case 2 -> menuCliente();
                case 0 -> { 
                    cooperativa.guardarDatos();
                    System.out.println("Datos guardados.");
                }
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private static void menuGestion() {
        int op;
        do {
            System.out.println("\n--- MENU DE GESTION ---");
            System.out.println("1. Registrar Conductor");
            System.out.println("2. Registrar Operador");
            System.out.println("3. Registrar Cliente");
            System.out.println("4. Atender Siguiente Solicitud");
            System.out.println("5. Ver Conductores");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerEntero();

            try {
                switch (op) {
                    case 1 -> registrarConductor();
                    case 2 -> registrarOperador();
                    case 3 -> registrarCliente();
                    case 4 -> atenderSolicitud();
                    case 5 -> mostrarConductores();
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (op != 0);
    }

    private static void menuCliente() {
        System.out.print("Ingrese su codigo de cliente: ");
        int cod = leerEntero();
        Cliente c = cooperativa.buscarClientePorCodigo(cod);

        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        int op;
        do {
            System.out.println("\n--- BIENVENIDO " + c.getNombre().toUpperCase() + " ---");
            System.out.println("1. Solicitar Viaje");
            System.out.println("2. Ver mi Historial");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerEntero();

            try {
                switch (op) {
                    case 1 -> pedirViaje(c);
                    case 2 -> c.mostrarHistorial();
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (op != 0);
    }

   
    private static void registrarConductor() throws Exception {
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Zona Inicial: "); String zona = sc.nextLine();
        System.out.print("Codigo: "); int cod = leerEntero();
        System.out.print("Placa: "); String placa = sc.nextLine();
        System.out.print("ID: "); String id = sc.nextLine();
        
        Conductor cond = new Conductor(nom, zona, cod, placa, id);
        System.out.println("Habilitar servicios (Estandar, Baul, Mascotas, Parrilla). Escriba 'fin' para terminar:");
        while(true) {
            String serv = sc.nextLine();
            if(serv.equalsIgnoreCase("fin")) break;
            try { cond.hablitarServicio(serv); } catch(Exception e) { System.out.println(e.getMessage()); }
        }
        cooperativa.registrarConductor(cond);
    }

    private static void registrarOperador() {
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Zona: "); String zona = sc.nextLine();
        System.out.print("Codigo: "); int cod = leerEntero();
        System.out.print("Turno: "); String turno = sc.nextLine();
        cooperativa.registrarOperador(new Operador(nom, zona, cod, turno));
    }

    private static void registrarCliente() {
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Zona: "); String zona = sc.nextLine();
        System.out.print("Codigo: "); int cod = leerEntero();
        cooperativa.registrarCliente(new Cliente(nom, zona, cod));
    }

    private static void pedirViaje(Cliente c) throws Exception {
        System.out.println("Barrios disponibles: " + String.join(", ", Mapa.Barrios));
        System.out.print("Origen: "); String ori = sc.nextLine();
        System.out.print("Destino: "); String des = sc.nextLine();
        System.out.print("Tipo de Taxi (Estandar/Baul/Mascotas/Parrilla): "); String tipo = sc.nextLine();
        
        cooperativa.crearSolicitud(c, ori, des, tipo);
        System.out.println("[Solicitud enviada] Un operador la procesara pronto.");
    }

    private static void atenderSolicitud() throws Exception {
        System.out.print("Codigo de Operador que atiende: ");
        int codOp = leerEntero();
        Operador op = cooperativa.buscarOperadorPorCodigo(codOp);
        if (op == null) {
            System.out.println("Operador no valido.");
            return;
        }
        cooperativa.atenderSolicitud(op);
    }

    private static void mostrarConductores() {
        for (int i = 0; i < cooperativa.getConductores().tamano(); i++) {
            System.out.println(cooperativa.getConductores().obtenerPorIndice(i));
        }
    }

    private static int leerEntero() {
        try {
            int n = Integer.parseInt(sc.nextLine());
            return n;
        } catch (Exception e) {
            return -1;
        }
    }
}