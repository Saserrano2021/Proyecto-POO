package Excepciones;

public class ZonaInexistenteExcepcion {
    public static class ZonaNoEncontradaException extends Exception {
        public ZonaNoEncontradaException(String message) {
            super("Error: La zona no fue encontrada. ");
        }
    }

}