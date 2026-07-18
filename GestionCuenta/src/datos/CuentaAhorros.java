package datos;

import java.io.Serializable;

public class CuentaAhorros extends Cuenta implements IRetirable, IInteres, Serializable {

    public CuentaAhorros(Cliente cliente, Long numero, double saldo) {
        super(cliente, numero, saldo);
    }

    @Override
    public void retirar(double monto) {
        if (monto <= saldo) saldo -= monto;
    }

    @Override
    public void aplicarInteres() {
        saldo += saldo * 0.03;
    }
}
