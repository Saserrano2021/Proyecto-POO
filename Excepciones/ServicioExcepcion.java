package Excepciones;

public class ServicioExcepcion {
    public static class ServicioNoEncontradoException extends Exception {
        public ServicioNoEncontradoException(String message) {
            super("Error: Este servicio no esta disponible en los servicios ofrecidos. ");
        }
    }
}
