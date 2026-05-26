package Excepciones;

public class ConductorExcepciones {
    public static class ConductorNoEncontradoException extends Exception {
        public ConductorNoEncontradoException(String message) {
            super("Error: El conductor no fue encontrado. ");
        }
    }
}