/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import javax.swing.JOptionPane;

public class CerrarCuentaFrame extends javax.swing.JFrame {

    // Variable para guardar la cuenta que encontramos
    private Cuenta cuentaEncontrada = null;
    
    // Variables de UI (generadas por NetBeans)
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea infoArea;

    /**
     * Creates new form CerrarCuentaFrame
     */
    public CerrarCuentaFrame() {
        initComponents();
        this.setLocationRelativeTo(null); // Centrar la ventana
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cerrar Cuenta Bancaria");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("ID de la Cuenta:");

        idField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        eliminarBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        eliminarBtn.setText("Eliminar Cuenta");
        eliminarBtn.setEnabled(false); // Inicia deshabilitado
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

        // --- Layout (código autogenerado por NetBeans) ---
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
    }// </editor-fold>                        

    // --- LÓGICA DE LOS BOTONES ---
    
    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Usamos el nuevo método de GestorCuentas
        cuentaEncontrada = GestorCuentas.buscarCuenta(id);

        if (cuentaEncontrada != null) {
            // PRC-33: Programar la lógica para mostrar la información de la cuenta
            infoArea.setText("CUENTA ENCONTRADA:\n\n" +
                             "ID Cliente: \t" + cuentaEncontrada.getId() + "\n" +
                             "Nombre: \t" + cuentaEncontrada.getNombreCliente() + "\n" +
                             "Saldo Actual: \t$" + cuentaEncontrada.getSaldo());
            
            // Activamos el botón de eliminar
            eliminarBtn.setEnabled(true);
        } else {
            infoArea.setText("No se encontró ninguna cuenta con el ID: " + id);
            cuentaEncontrada = null; // Limpiamos la variable
            eliminarBtn.setEnabled(false); // Desactivamos el botón
        }
    }                                         

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if (cuentaEncontrada == null) {
            JOptionPane.showMessageDialog(this, "Error: No hay una cuenta seleccionada para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // PRC-35: Diseñar la ventana de confirmación
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
                // Si el usuario confirma, llamamos al método del gestor
                // Este método ya incluye las validaciones (PRC-34, 36, 37)
                GestorCuentas.eliminarCuenta(cuentaEncontrada.getId());
                
                JOptionPane.showMessageDialog(this, "Cuenta eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); // Cierra la ventana de "Cerrar Cuenta"
                
            } catch (Exception e) {
                // Captura el error (ej. si tiene saldo) (PRC-36)
                JOptionPane.showMessageDialog(this, "Error al eliminar:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
                // Refrescamos la info por si el saldo cambió
                buscarBtnActionPerformed(null);
            }
        }
        if (respuesta == JOptionPane.NO_OPTION) {
    JOptionPane.showMessageDialog(this, "No se elimino cuenta");
    return;
}
    }                                           

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.dispose(); // Cierra esta ventana
    }
}