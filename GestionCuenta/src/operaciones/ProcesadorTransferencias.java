package operaciones;

import datos.Cuenta;
import datos.IRetirable;

public class ProcesadorTransferencias {

    private Notificador notificador;

    public ProcesadorTransferencias(Notificador notificador) {
        this.notificador = notificador;
    }

    public void transferir(IRetirable origen, Cuenta destino, double monto) {
        origen.retirar(monto);
        destino.depositar(monto);
        notificador.enviar("Transferencia de $" + monto + " exitosa.");
    }
}
