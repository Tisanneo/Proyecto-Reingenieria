/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HistorialDesactivadasFrame extends javax.swing.JFrame {

    // Componentes UI
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JTextField idField;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHistorial;
    private javax.swing.JButton cerrarBtn;

    public HistorialDesactivadasFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        inicializarTabla();
    }

    private void inicializarTabla() {
        String[] columnas = {"Detalle (Cuentas Cerradas)"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaHistorial.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        cerrarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Historial Desactivadas");

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Historial de Cuentas Cerradas");

        jLabelId.setText("ID de Cuenta Cerrada:");

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tablaHistorial);

        cerrarBtn.setText("Cerrar");
        cerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // CORRECCIÓN DEL ERROR DE DISPOSE
                HistorialDesactivadasFrame.this.dispose();
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelId)
                        .addGap(18, 18, 18)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }

    // --- LÓGICA (PRC-107, 108, 110, 119) ---

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String id = idField.getText().trim();
        if (id.isEmpty()) return;

        inicializarTabla();
        DefaultTableModel modelo = (DefaultTableModel) tablaHistorial.getModel();

        // 1. Buscamos en el archivo de CERRADAS (PRC-108)
        Cuenta cuenta = GestorCuentas.buscarCuentaCerrada(id);

        if (cuenta != null) {
            ArrayList<String> historial = new ArrayList<>(cuenta.getHistorial());
            Collections.reverse(historial); // Las más recientes primero

            // PRC-110: Máximo 10 transacciones
            int limite = 10;
            int contador = 0;
            
            for (String transaccion : historial) {
                if (contador >= limite) break; 
                modelo.addRow(new Object[]{transaccion}); // PRC-119
                contador++;
            }
            
            JOptionPane.showMessageDialog(this, "Mostrando historial de cuenta cerrada: " + cuenta.getNombreCliente());

        } else {
            JOptionPane.showMessageDialog(this, "No se encontró cuenta CERRADA con ese ID.\n(Puede que la cuenta siga activa o no exista)", "Sin Resultados", JOptionPane.WARNING_MESSAGE);
        }
    }
}