package datos;

import java.io.Serializable;

public class Cuenta implements Serializable {
    private Long numero;
    protected double saldo;
    private Cliente cliente;

    public Cuenta(Cliente cliente, Long numero, double saldo) {
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente obtenerCliente() {
        return cliente;
    }

    public Long obtenerNumero() {
        return numero;
    }
}
