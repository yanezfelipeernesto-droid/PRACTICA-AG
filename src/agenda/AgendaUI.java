package agenda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AgendaUI extends JFrame {

    private final Agenda agenda;

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;

    private JTextArea areaSalida;
    private JLabel lblEstado;

    public AgendaUI() {
        // Intentar usar Nimbus (aspecto moderno sin dependencias)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Si no, usa el defecto
        }

        this.agenda = new Agenda();

        setTitle("Agenda de Contactos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(15, 15));
        root.setBorder(new EmptyBorder(15, 15, 15, 15));
        root.setBackground(new Color(240, 240, 240));
        setContentPane(root);

        root.add(crearPanelSuperior(), BorderLayout.NORTH);

        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setLineWrap(true);
        areaSalida.setWrapStyleWord(true);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaSalida.setBackground(Color.WHITE);
        areaSalida.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBorder(null);
        root.add(scroll, BorderLayout.CENTER);

        lblEstado = new JLabel();
        lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblEstado.setBorder(new EmptyBorder(5, 5, 5, 5));
        root.add(lblEstado, BorderLayout.SOUTH);

        escribirSalida("Agenda creada. Capacidad: 10");
        actualizarEstado();

        setVisible(true);
    }

    private JPanel crearPanelSuperior() {
        JPanel contenedor = new JPanel(new BorderLayout(10, 10));
        contenedor.setOpaque(false);

        // Panel de campos de texto (GridBag para alineación)
        JPanel panelInputs = new JPanel(new GridBagLayout());
        panelInputs.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInputs.add(crearEtiqueta("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        txtNombre.setToolTipText("Nombre (obligatorio)");
        panelInputs.add(txtNombre, gbc);

        // Fila 0: Apellido
        gbc.gridx = 2;
        panelInputs.add(crearEtiqueta("Apellido:"), gbc);
        gbc.gridx = 3;
        txtApellido = new JTextField(15);
        txtApellido.setToolTipText("Apellido (obligatorio)");
        panelInputs.add(txtApellido, gbc);

        // Fila 0: Teléfono
        gbc.gridx = 4;
        panelInputs.add(crearEtiqueta("Teléfono:"), gbc);
        gbc.gridx = 5;
        txtTelefono = new JTextField(15);
        txtTelefono.setToolTipText("Mínimo 10 caracteres");
        panelInputs.add(txtTelefono, gbc);

        contenedor.add(panelInputs, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setOpaque(false);

        panelBotones.add(crearBoton("➕ Añadir", new Color(70, 130, 180), e -> onAgregar()));
        panelBotones.add(crearBoton("🔍 Buscar", new Color(60, 179, 113), e -> onBuscar()));
        panelBotones.add(crearBoton("❌ Eliminar", new Color(220, 20, 60), e -> onEliminar()));
        panelBotones.add(crearBoton("📋 Listar", new Color(255, 165, 0), e -> onListar()));
        panelBotones.add(crearBoton("🧹 Limpiar", new Color(128, 128, 128), e -> onLimpiar()));

        contenedor.add(panelBotones, BorderLayout.SOUTH);

        return contenedor;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        return label;
    }

    private JButton crearBoton(String texto, Color colorFondo, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 13));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorFondo);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorFondo.darker(), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorFondo.brighter());
            }
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorFondo);
            }
        });

        boton.addActionListener(accion);
        return boton;
    }

    // ---------- Acciones ----------
    private void onAgregar() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            mostrarMensaje("Completa todos los campos.");
            return;
        }

        try {
            Contacto c = new Contacto(nombre, apellido, telefono);
            agenda.añadirContacto(c);
            escribirSalida("✅ Contacto añadido: " + nombre + " " + apellido);
        } catch (IllegalArgumentException e) {
            mostrarMensaje("Error: " + e.getMessage());
        }

        refrescarListaYEstado();
    }

    private void onBuscar() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarMensaje("Escribe nombre y apellido para buscar.");
            return;
        }

        Contacto encontrado = buscarEnLista(nombre, apellido);
        if (encontrado == null) {
            escribirSalida("🔎 No encontrado: " + nombre + " " + apellido);
        } else {
            escribirSalida("🔎 Encontrado: " + encontrado);
            txtTelefono.setText(encontrado.getTelefono());
        }
    }

    private void onEliminar() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarMensaje("Escribe nombre y apellido del contacto a eliminar.");
            return;
        }

        // Teléfono ficticio válido para la comparación
        Contacto c = new Contacto(nombre, apellido, "0000000000");
        agenda.eliminarContacto(c);
        escribirSalida("🗑️ Contacto eliminado: " + nombre + " " + apellido);
        refrescarListaYEstado();
    }

    private void onListar() {
        ArrayList<Contacto> contactos = agenda.getContactos();
        if (contactos.isEmpty()) {
            escribirSalida("📭 Agenda vacía.");
            return;
        }

        StringBuilder sb = new StringBuilder("📒 Contactos:\n");
        for (Contacto c : contactos) {
            sb.append("- ").append(c).append("\n");
        }
        escribirSalida(sb.toString());
    }

    private void onLimpiar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        areaSalida.setText("");
        actualizarEstado();
    }

    // ---------- Helpers ----------
    private void refrescarListaYEstado() {
        onListar();
        actualizarEstado();
    }

    private void actualizarEstado() {
        int libres = agenda.espacioLibre();
        boolean llena = agenda.agendaLlena();
        lblEstado.setText(String.format("Espacios libres: %d / 10 | ¿Llena? %s", libres, llena ? "Sí" : "No"));
    }

    private void escribirSalida(String mensaje) {
        areaSalida.append(mensaje + "\n");
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    private Contacto buscarEnLista(String nombre, String apellido) {
        for (Contacto c : agenda.getContactos()) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaUI());
    }
}