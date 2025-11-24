/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultarCuentasFrame extends javax.swing.JFrame {

    private javax.swing.JButton cerrarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCuentas;

    public ConsultarCuentasFrame() {
        initComponents();
        this.setLocationRelativeTo(null); 
        if (GestorCuentas.cargarCuentas().isEmpty()) {
        JOptionPane.showMessageDialog(this, "No hay cuentas registradas en el sistema.");
        this.dispose();
        return;
}
        cargarDatosEnTabla();
    }

    private void cargarDatosEnTabla() {
        String[] columnas = {"ID Cuenta", "Nombre Cliente", "Saldo ($)"};
        
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
     
        ArrayList<Cuenta> listaCuentas = GestorCuentas.cargarCuentas();

        if (listaCuentas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuentas registradas en el sistema.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Cuenta c : listaCuentas) {
                Object[] fila = {
                    c.getId(),
                    c.getNombreCliente(),
                    String.format("%.2f", c.getSaldo()) 
                };
                modelo.addRow(fila);
            }
        }
        
        tablaCuentas.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuentas = new javax.swing.JTable();
        cerrarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Cuentas");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Listado de Cuentas Registradas");

        // Configuración inicial de la tabla (el modelo se sobreescribe en cargarDatosEnTabla)
        tablaCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID Cuenta", "Nombre Cliente", "Saldo"}
        ));
        jScrollPane1.setViewportView(tablaCuentas);

        cerrarBtn.setText("Cerrar");
        cerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarBtnActionPerformed(evt);
            }
        });

        // --- Layout (Autogenerado) ---
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cerrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cerrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>                        

    private void cerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.dispose();
    }                                         
}