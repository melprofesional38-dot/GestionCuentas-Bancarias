package gui;

import datos.Cuenta;
import operaciones.OperacionesBanco;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListaCuentas extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private OperacionesBanco operacionesBanco;

    public VentanaListaCuentas(OperacionesBanco operacionesBanco) {
        this.operacionesBanco = operacionesBanco;

        setTitle("Listado de Cuentas");
        setSize(800, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inicializarTabla();
        cargarDatos();
    }

    private void inicializarTabla() {
        String[] columnas = {"Nombre cliente", "Identificación", "Número cuenta", "Saldo", "Tipo"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 20, 740, 400);
        add(scroll);
    }

    private void cargarDatos() {
        List<Cuenta> cuentas = operacionesBanco.obtenerTodos();
        modelo.setRowCount(0);
        for (Cuenta laCuenta : cuentas) {
            String nombreCliente  = laCuenta.obtenerCliente() != null ? laCuenta.obtenerCliente().obtenerNombre()         : "Sin nombre";
            String identificacion = laCuenta.obtenerCliente() != null ? laCuenta.obtenerCliente().obtenerIdentificacion() : "Sin ID";
            String tipo = laCuenta.obtenerNumero().toString().startsWith("5425") ? "AHORROS" : "CORRIENTE";
            modelo.addRow(new Object[]{nombreCliente, identificacion, laCuenta.obtenerNumero(), laCuenta.getSaldo(), tipo});
        }
    }
}
