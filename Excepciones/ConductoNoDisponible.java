package Excepciones;

import  Sistema.Conductor;

public class ConductoNoDisponible {
    public static class ConductorNoDisponibleException extends Exception {
        public ConductorNoDisponibleException(Conductor conductor) {
            super("Error: El conductor" + conductor.getNombre() + " no está disponible para este servicio. ");
        }
    }
}
