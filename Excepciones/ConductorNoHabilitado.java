package Excepciones;

public class ConductorNoHabilitado {
    public static class ConductorNoHabilitadoException extends Exception {
        public ConductorNoHabilitadoException(String message) {
            super("Error: El conductor no está habilitado para este servicio. ");
        }
    }
}
