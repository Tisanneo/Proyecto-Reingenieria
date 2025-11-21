/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import java.util.ArrayList;
import java.util.Collections; // Para invertir la lista (ver las más recientes primero)
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HistorialActivasFrame extends javax.swing.JFrame {

    // Componentes UI
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JTextField idField;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHistorial;
    private javax.swing.JButton cerrarBtn;

    public HistorialActivasFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        inicializarTabla();
    }

    private void inicializarTabla() {
        // PRC-93: Diseñar la UI de la tabla de historial
        // Solo necesitamos una columna ancha para la descripción
        String[] columnas = {"Detalle de la Transacción"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Solo lectura
            }
        };
        tablaHistorial.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        cerrarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Historial de Transacciones (Activas)");

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Historial de Cuenta Activa");

        jLabelId.setText("Ingrese ID de Cuenta:");

        buscarBtn.setText("Ver Historial");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Detalle" }
        ));
        jScrollPane1.setViewportView(tablaHistorial);

        cerrarBtn.setText("Cerrar");
        cerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistorialActivasFrame.this.dispose(); // <--- ASÍ DEBE QUEDAR
            }
        });

        // --- Layout ---
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE) // Auto-ajustable
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelId)
                        .addGap(18, 18, 18)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarBtn))
                    .addComponent(cerrarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelTitle)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cerrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>

    // --- LÓGICA (PRC-85, 86, 91, 109) ---

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Limpiamos la tabla
        inicializarTabla();
        DefaultTableModel modelo = (DefaultTableModel) tablaHistorial.getModel();

        // 1. Buscamos la cuenta (PRC-85 y PRC-86 implícito)
        Cuenta cuenta = GestorCuentas.buscarCuenta(id);

        if (cuenta != null) {
            // PRC-85: Obtener transacciones
            ArrayList<String> historialCompleto = cuenta.getHistorial();
            
            // Creamos una copia para no alterar el original y poder invertirla
            ArrayList<String> historialParaMostrar = new ArrayList<>(historialCompleto);
            
            // Invertimos para que salgan las más recientes arriba (opcional pero recomendado UX)
            Collections.reverse(historialParaMostrar);

            // PRC-91: Programar el límite de 20 transacciones
            int contador = 0;
            for (String transaccion : historialParaMostrar) {
                if (contador >= 20) break; // Detenerse al llegar a 20

                // PRC-109: Poblar la tabla
                modelo.addRow(new Object[]{transaccion});
                contador++;
            }
            
            if (historialCompleto.isEmpty()) {
                modelo.addRow(new Object[]{"Sin movimientos registrados."});
            }

        } else {
            JOptionPane.showMessageDialog(this, "Cuenta no encontrada o no está activa.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}