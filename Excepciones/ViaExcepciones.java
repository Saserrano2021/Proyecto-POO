package Excepciones;

public class ViaExcepciones {
    public static class ViaNoEncontradaException extends Exception {
        public ViaNoEncontradaException(String message) {
            super("Error: La vía no fue encontrada. ");
        }
    }

}
