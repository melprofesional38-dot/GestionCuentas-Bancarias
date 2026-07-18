package operaciones;

public class ServicioEmail implements Notificador {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando Email: " + mensaje);
    }
}
