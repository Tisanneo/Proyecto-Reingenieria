/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import javax.swing.JOptionPane;

public class CerrarCuentaFrame extends javax.swing.JFrame {

    private Cuenta cuentaEncontrada = null;
    
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea infoArea;

    public CerrarCuentaFrame() {
        initComponents();
        this.setLocationRelativeTo(null); 
    }

    @SuppressWarnings("unchecked")                   
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoArea = new javax.swing.JTextArea();
        eliminarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cerrar Cuenta");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cerrar Cuenta Bancaria");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel2.setText("ID de la Cuenta:");

        idField.setFont(new java.awt.Font("Segoe UI", 0, 14)); 

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        infoArea.setEditable(false);
        infoArea.setColumns(20);
        infoArea.setRows(5);
        jScrollPane1.setViewportView(infoArea);

        eliminarBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        eliminarBtn.setText("Eliminar Cuenta");
        eliminarBtn.setEnabled(false); 
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
    }                     

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cuentaEncontrada = GestorCuentas.buscarCuenta(id);

        if (cuentaEncontrada != null) {
            infoArea.setText("CUENTA ENCONTRADA:\n\n" +
                             "ID Cliente: \t" + cuentaEncontrada.getId() + "\n" +
                             "Nombre: \t" + cuentaEncontrada.getNombreCliente() + "\n" +
                             "Saldo Actual: \t$" + cuentaEncontrada.getSaldo());
            
            eliminarBtn.setEnabled(true);
        } else {
            infoArea.setText("No se encontró ninguna cuenta con el ID: " + id);
            cuentaEncontrada = null; 
            eliminarBtn.setEnabled(false); 
        }
    }                                         

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if (cuentaEncontrada == null) {
            JOptionPane.showMessageDialog(this, "Error: No hay una cuenta seleccionada para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this, 
                "¿Está seguro de que desea eliminar la cuenta de '" + cuentaEncontrada.getNombreCliente() + "'?\n" +
                "ID: " + cuentaEncontrada.getId() + "\n" +
                "Esta acción no se puede deshacer.",
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                GestorCuentas.eliminarCuenta(cuentaEncontrada.getId());
                
                JOptionPane.showMessageDialog(this, "Cuenta eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                buscarBtnActionPerformed(null);
            }
        }
        if (respuesta == JOptionPane.NO_OPTION) {
    JOptionPane.showMessageDialog(this, "No se elimino cuenta");
    return;
}
    }                                           

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.dispose(); 
    }
}