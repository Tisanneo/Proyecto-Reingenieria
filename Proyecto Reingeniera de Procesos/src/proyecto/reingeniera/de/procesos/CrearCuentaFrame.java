/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import javax.swing.JOptionPane;

public class CrearCuentaFrame extends javax.swing.JFrame {

    // Variables de la UI generadas por NetBeans
    private javax.swing.JButton guardarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField saldoField;

    public CrearCuentaFrame() {
        initComponents();
        // Centra la ventana al aparecer
        this.setLocationRelativeTo(null); 
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        saldoField = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Nueva Cuenta");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Crear Nueva Cuenta");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre del Cliente:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Saldo Inicial:");

        guardarBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        guardarBtn.setText("Guardar");
        
        
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        // --- Layout (código de NetBeans autogenerado) ---
        // (Este layout es básico, puedes ajustarlo en el diseñador)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombreField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(saldoField))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(saldoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        pack();
    }
    
    // --- Lógica de los botones ---

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // Simplemente cierra esta ventana sin hacer nada
        this.dispose();
    }                                           

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String nombre = nombreField.getText();
        String saldoStr = saldoField.getText();
        double saldo;

        // PRC-27: Programar la validación de entrada (ej. que el saldo sea un número).
        
        // Validación de nombre vacío
        if (nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del cliente no puede estar vacío.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de saldo
        try {
            saldo = Double.parseDouble(saldoStr);
            if (saldo < 0) {
                JOptionPane.showMessageDialog(this, "El saldo inicial no puede ser negativo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El saldo debe ser un número válido (ej. 1500.50).", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (saldo == 0) {
        JOptionPane.showMessageDialog(this, "No puede crear cuenta con 0 de saldo");
        return;
        }
        if (!nombre.matches("[a-zA-Z ]+")) {
        JOptionPane.showMessageDialog(this, "No se puede ingresar numeros en el nombre");
    return;
}
        // Si todas las validaciones pasan, llamamos al Gestor
        try {
            // Aquí se ejecuta la lógica de PRC-24, 25, 26, 28
            String nuevoId = GestorCuentas.crearNuevaCuenta(nombre, saldo);
            
            JOptionPane.showMessageDialog(this, 
                    "¡Cuenta creada exitosamente!\nID Asignado: " + nuevoId, 
                    "Creación Exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            // Cierra la ventana de "Crear Cuenta"
            this.dispose(); 
            
        } catch (Exception e) {
            // Captura cualquier error del Gestor (ej. límite de 10 cuentas)
            JOptionPane.showMessageDialog(this, 
                    "Error al crear la cuenta:\n" + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }                                          
}