package datos;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nombre;
    private String identificacion;

    public Cliente(String nombre, String identificacion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerIdentificacion() {
        return identificacion;
    }
}
