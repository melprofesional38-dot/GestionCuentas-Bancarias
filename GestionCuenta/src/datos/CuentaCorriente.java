package datos;

import java.io.Serializable;

public class CuentaCorriente extends Cuenta implements IRetirable, IInteres, Serializable {

    public CuentaCorriente(Cliente cliente, Long numero, double saldo) {
        super(cliente, numero, saldo);
    }

    @Override
    public void retirar(double monto) {
        if (monto <= saldo) saldo -= monto;
    }

    @Override
    public void aplicarInteres() {
        saldo += saldo * 0.01;
    }
}
