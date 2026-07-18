package gui;

import datos.Cliente;
import datos.Cuenta;
import datos.CuentaAhorros;
import datos.CuentaCorriente;
import operaciones.OperacionesBanco;
import util.SoloNumerosFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class FormularioCuenta extends JFrame {

    // ── Componentes UI ───────────────────────────────────────────────────────
    private JLabel lblTitulo;
    private JLabel lblIdCliente;
    public JTextField txtIdCliente;
    private JLabel lblNombre;
    public JTextField txtNombreCliente;
    private JLabel lblNumeroCuenta;
    public JTextField txtNumeroCuenta;
    private JLabel lblSaldo;
    public JFormattedTextField txtSaldoInicial;
    private JLabel lblTipoCuenta;
    public JRadioButton rbAhorros;
    public JRadioButton rbCorriente;
    public JButton btnGenerar;
    public JButton btnRegistrar;
    public JButton btnVerCuentas;

    // ── Estado interno ───────────────────────────────────────────────────────
    private Long numeroTmpAhorros;
    private Long numeroTmpCorriente;
    private final OperacionesBanco operacionesBanco;

    // ── Constructor ──────────────────────────────────────────────────────────
    public FormularioCuenta(OperacionesBanco operacionesBanco) {
        this.operacionesBanco = operacionesBanco;
        setTitle("Banco Kurama");
        setSize(700, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        getContentPane().setLayout(null);
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        // ── Header azul ──────────────────────────────────────────────────────
        JPanel panelHeader = new JPanel();
        panelHeader.setBounds(0, 0, 700, 50);
        panelHeader.setBackground(new Color(30, 90, 160));
        panelHeader.setLayout(null);
        getContentPane().add(panelHeader);

        lblTitulo = new JLabel("Banco Kurama", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(180, 10, 340, 30);
        panelHeader.add(lblTitulo);

        // ── Campos izquierda ─────────────────────────────────────────────────
        lblIdCliente = new JLabel("Identificación cliente:");
        lblIdCliente.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblIdCliente.setBounds(20, 75, 180, 25);
        getContentPane().add(lblIdCliente);

        txtIdCliente = new JTextField();
        txtIdCliente.setFont(new Font("Verdana", Font.PLAIN, 13));
        txtIdCliente.setBounds(210, 75, 190, 28);
        getContentPane().add(txtIdCliente);

        lblNombre = new JLabel("Nombre Cliente:");
        lblNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblNombre.setBounds(20, 123, 180, 25);
        getContentPane().add(lblNombre);

        txtNombreCliente = new JTextField();
        txtNombreCliente.setFont(new Font("Verdana", Font.PLAIN, 13));
        txtNombreCliente.setBounds(210, 123, 190, 28);
        getContentPane().add(txtNombreCliente);

        lblNumeroCuenta = new JLabel("Número de cuenta:");
        lblNumeroCuenta.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblNumeroCuenta.setBounds(20, 171, 180, 25);
        getContentPane().add(lblNumeroCuenta);

        txtNumeroCuenta = new JTextField();
        txtNumeroCuenta.setFont(new Font("Verdana", Font.PLAIN, 13));
        txtNumeroCuenta.setEditable(false);
        txtNumeroCuenta.setBackground(new Color(235, 235, 235));
        txtNumeroCuenta.setBounds(210, 171, 190, 28);
        getContentPane().add(txtNumeroCuenta);

        lblSaldo = new JLabel("Saldo Inicial:");
        lblSaldo.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblSaldo.setBounds(20, 219, 180, 25);
        getContentPane().add(lblSaldo);

        txtSaldoInicial = new JFormattedTextField();
        txtSaldoInicial.setFont(new Font("Verdana", Font.PLAIN, 13));
        txtSaldoInicial.setBounds(210, 219, 190, 28);
        getContentPane().add(txtSaldoInicial);
        // Filtro: solo dígitos y un punto decimal
        ((AbstractDocument) txtSaldoInicial.getDocument()).setDocumentFilter(new SoloNumerosFilter());

        // ── Separador vertical ───────────────────────────────────────────────
        JSeparator lineaVertical = new JSeparator(JSeparator.VERTICAL);
        lineaVertical.setBounds(430, 60, 2, 290);
        lineaVertical.setForeground(new Color(200, 200, 200));
        getContentPane().add(lineaVertical);

        // ── Panel derecho: tipo de cuenta ────────────────────────────────────
        lblTipoCuenta = new JLabel("Tipo de cuenta:");
        lblTipoCuenta.setFont(new Font("Verdana", Font.BOLD, 13));
        lblTipoCuenta.setBounds(460, 90, 200, 25);
        getContentPane().add(lblTipoCuenta);

        rbAhorros = new JRadioButton("Ahorros");
        rbAhorros.setFont(new Font("Verdana", Font.PLAIN, 13));
        rbAhorros.setBackground(new Color(245, 245, 245));
        rbAhorros.setBounds(460, 125, 150, 25);
        getContentPane().add(rbAhorros);

        rbCorriente = new JRadioButton("Corriente");
        rbCorriente.setFont(new Font("Verdana", Font.PLAIN, 13));
        rbCorriente.setBackground(new Color(245, 245, 245));
        rbCorriente.setBounds(460, 158, 150, 25);
        getContentPane().add(rbCorriente);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbAhorros);
        grupo.add(rbCorriente);

        // Al seleccionar tipo genera número automáticamente
        rbAhorros.addActionListener(e -> generar());
        rbCorriente.addActionListener(e -> generar());

        // ── Botones ──────────────────────────────────────────────────────────
        btnGenerar = new JButton("Generar Cuenta");
        btnGenerar.setFont(new Font("Verdana", Font.BOLD, 13));
        btnGenerar.setBackground(new Color(70, 130, 210));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);
        btnGenerar.setBorderPainted(false);
        btnGenerar.setBounds(20, 290, 185, 32);
        btnGenerar.addActionListener(e -> generar());
        getContentPane().add(btnGenerar);

        btnRegistrar = new JButton("Registrar Cuenta");
        btnRegistrar.setFont(new Font("Verdana", Font.BOLD, 13));
        btnRegistrar.setBackground(new Color(30, 140, 90));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setBounds(215, 290, 185, 32);
        btnRegistrar.addActionListener(e -> registrar());
        getContentPane().add(btnRegistrar);

        btnVerCuentas = new JButton("Ver Cuentas");
        btnVerCuentas.setFont(new Font("Verdana", Font.BOLD, 13));
        btnVerCuentas.setBackground(new Color(120, 60, 180));
        btnVerCuentas.setForeground(Color.WHITE);
        btnVerCuentas.setFocusPainted(false);
        btnVerCuentas.setBorderPainted(false);
        btnVerCuentas.setBounds(460, 290, 200, 32);
        btnVerCuentas.addActionListener(e -> verCuentas());
        getContentPane().add(btnVerCuentas);
    }

    /**
     * Genera (o recupera) el número de cuenta según el tipo seleccionado.
     */
    private void generar() {
        if (rbAhorros.isSelected()) {
            if (this.numeroTmpAhorros == null)
                this.numeroTmpAhorros = operacionesBanco.obtenerNumeroCuenta(true);
            txtNumeroCuenta.setText(this.numeroTmpAhorros.toString());

        } else if (rbCorriente.isSelected()) {
            if (this.numeroTmpCorriente == null)
                this.numeroTmpCorriente = operacionesBanco.obtenerNumeroCuenta(false);
            txtNumeroCuenta.setText(this.numeroTmpCorriente.toString());

        } else {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un tipo de cuenta.", "Tenga en cuenta:", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida campos y registra la cuenta, persistiendo en disco.
     */
    private void registrar() {
        if (txtNumeroCuenta.getText().isEmpty() || txtNombreCliente.getText().isEmpty()
                || txtSaldoInicial.getText().isEmpty() || txtIdCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Algún dato está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double saldo;
        try {
            saldo = Double.parseDouble(txtSaldoInicial.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El saldo debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (saldo < 0) {
            JOptionPane.showMessageDialog(this,
                    "El saldo no puede ser menor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(txtNombreCliente.getText(), txtIdCliente.getText());
        Long numeroCuenta = Long.parseLong(txtNumeroCuenta.getText());
        Cuenta cuenta;
        if (txtNumeroCuenta.getText().startsWith("5425")) {
            cuenta = new CuentaAhorros(cliente, numeroCuenta, saldo);
        } else {
            cuenta = new CuentaCorriente(cliente, numeroCuenta, saldo);
        }

        operacionesBanco.agregarCuenta(cuenta);

        JOptionPane.showMessageDialog(this,
                "Cuenta creada: " + cuenta.obtenerNumero()
                        + "\nCliente: " + cuenta.obtenerCliente().obtenerNombre(),
                "Información", JOptionPane.INFORMATION_MESSAGE);

        limpiar();
    }

    /**
     * Abre la ventana con la lista de todas las cuentas registradas.
     */
    private void verCuentas() {
        new VentanaListaCuentas(operacionesBanco).setVisible(true);
    }

    private void limpiar() {
        txtNumeroCuenta.setText("");
        txtSaldoInicial.setText("");
        txtNombreCliente.setText("");
        txtIdCliente.setText("");
        rbAhorros.setSelected(false);
        rbCorriente.setSelected(false);
        // Resetear temporales para que el próximo registro genere números nuevos
        numeroTmpAhorros = null;
        numeroTmpCorriente = null;
    }
}
