package operaciones;

import datos.Cuenta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class OperacionesBanco {
    private List<Long> numerosCuentas;
    private List<Cuenta> cuentas;
    private final String ARCHIVO_CUENTAS = "cuentas.mio";
    private final String ARCHIVO_NUMCUENTAS = "numeroscuentas.mio";

    public OperacionesBanco() {
        try {
            this.cuentas = util.UtilidadArchivos.leer(ARCHIVO_CUENTAS);
        } catch (IOException e) {
            this.cuentas = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.err.println("Error " + e);
            this.cuentas = new ArrayList<>();
        }
        try {
            this.numerosCuentas = util.UtilidadArchivos.leer(ARCHIVO_NUMCUENTAS);
        } catch (IOException e) {
            this.numerosCuentas = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.err.println("Error " + e);
            this.numerosCuentas = new ArrayList<>();
        }
    }

    /**
     * Agrega una cuenta completa con cliente y persiste en disco.
     */
    public void agregarCuenta(Cuenta laCuenta) {
        this.cuentas.add(laCuenta);
        this.numerosCuentas.add(laCuenta.obtenerNumero());
        try {
            util.UtilidadArchivos.guardar(this.cuentas, this.ARCHIVO_CUENTAS);
        } catch (IOException e) {
            System.err.println("Error al guardar cuentas: " + e);
        }
        try {
            util.UtilidadArchivos.guardar(this.numerosCuentas, this.ARCHIVO_NUMCUENTAS);
        } catch (IOException e) {
            System.err.println("Error al guardar números de cuentas: " + e);
        }
    }

    /**
     * Verifica si un número de cuenta ya existe (evita duplicados).
     */
    public boolean validarCuenta(Long numeroBuscar) {
        Optional<Long> numero = this.numerosCuentas
                .stream()
                .filter(n -> n.equals(numeroBuscar))
                .findFirst();
        return numero.isPresent();
    }

    /**
     * Genera un número de cuenta único según el tipo (ahorros / corriente).
     */
    public Long obtenerNumeroCuenta(boolean esAhorros) {
        Long numeroCuenta;
        boolean existe;
        do {
            numeroCuenta = Long.parseLong(generarNumeroAleatorio(esAhorros));
            existe = validarCuenta(numeroCuenta);
        } while (existe);
        return numeroCuenta;
    }

    private String generarNumeroAleatorio(boolean esAhorros) {
        int numero = ThreadLocalRandom.current().nextInt(1000, 10000);
        return (esAhorros ? "5425" : "8545") + numero;
    }

    /**
     * Valida con for clásico.
     */
    public boolean validarCuentaOP1(Long numeroBuscar) {
        for (int i = 0; i < this.numerosCuentas.size(); i++) {
            if (numeroBuscar.equals(this.numerosCuentas.get(i))) return true;
        }
        return false;
    }

    /**
     * Valida usando foreach.
     */
    public boolean validarCuentaOP2(Long numeroBuscar) {
        for (Long numero : this.numerosCuentas) {
            if (numeroBuscar.equals(numero)) return true;
        }
        return false;
    }

    /**
     * Devuelve la lista completa de cuentas registradas.
     */
    public List<Cuenta> obtenerTodos() {
        return this.cuentas;
    }
}
