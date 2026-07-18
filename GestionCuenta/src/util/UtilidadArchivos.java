package util;

import java.io.*;

/**
 * Clase para leer o almacenar datos binarios.
 */
public class UtilidadArchivos {

    /**
     * Guarda cualquier objeto que implemente Serializable.
     * @param objeto El objeto a guardar (List, Cuenta, etc.)
     * @param ruta   Ruta del archivo destino.
     */
    public static <T> void guardar(T objeto, String ruta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(objeto);
        }
    }

    /**
     * Lee un objeto del archivo y lo devuelve con el tipo correcto.
     * @param <T>  El tipo de dato esperado.
     * @param ruta Ruta del archivo a leer.
     */
    @SuppressWarnings("unchecked")
    public static <T> T leer(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (T) ois.readObject();
        }
    }
}
