/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import javax.swing.JOptionPane;

public class ModificarCuentaFrame extends javax.swing.JFrame {

    private Cuenta cuentaActual = null;

    // Componentes UI
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField nombreActualField;
    private javax.swing.JTextField saldoField;
    private javax.swing.JTextField nuevoNombreField;
    private javax.swing.JLabel jLabel1;     // Título
    private javax.swing.JLabel jLabelID;    // Etiqueta ID
    private javax.swing.JLabel jLabelNom;   // Etiqueta Nombre Actual
    private javax.swing.JLabel jLabelSaldo; // Etiqueta Saldo
    private javax.swing.JLabel jLabelNuevo; // Etiqueta Nuevo Nombre

    public ModificarCuentaFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelID = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        
        jLabelNom = new javax.swing.JLabel();
        nombreActualField = new javax.swing.JTextField();
        
        jLabelSaldo = new javax.swing.JLabel();
        saldoField = new javax.swing.JTextField();
        
        jLabelNuevo = new javax.swing.JLabel();
        nuevoNombreField = new javax.swing.JTextField();
        
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Cuenta");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modificar Nombre de Cliente");

        jLabelID.setText("ID Cuenta:");
        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(evt -> buscarBtnActionPerformed(evt));

        jLabelNom.setText("Nombre Actual:");
        nombreActualField.setEditable(false); // PRC-30: Solo lectura

        jLabelSaldo.setText("Saldo Actual:");
        saldoField.setEditable(false);        // Solo lectura

        jLabelNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jLabelNuevo.setText("Nuevo Nombre:"); // PRC-30: Este sí será editable

        guardarBtn.setText("Guardar Cambios");
        guardarBtn.setEnabled(false); // Desactivado hasta que se busque
        guardarBtn.addActionListener(evt -> guardarBtnActionPerformed(evt));

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(evt -> this.dispose());

        // --- Layout (Ajustar en NetBeans Design si es necesario) ---
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelID)
                            .addComponent(jLabelNom)
                            .addComponent(jLabelSaldo)
                            .addComponent(jLabelNuevo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscarBtn))
                            .addComponent(nombreActualField)
                            .addComponent(saldoField)
                            .addComponent(nuevoNombreField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardarBtn)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelID)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNom)
                    .addComponent(nombreActualField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSaldo)
                    .addComponent(saldoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNuevo)
                    .addComponent(nuevoNombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>                        

    // --- LÓGICA ---

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // PRC-29: Buscar la cuenta para editar
        cuentaActual = GestorCuentas.buscarCuenta(id);

        if (cuentaActual != null) {
            nombreActualField.setText(cuentaActual.getNombreCliente());
            saldoField.setText(String.valueOf(cuentaActual.getSaldo()));
            nuevoNombreField.setText(""); // Limpiar campo de nuevo nombre
            guardarBtn.setEnabled(true);  // Habilitar botón de guardar
        } else {
            JOptionPane.showMessageDialog(this, "Cuenta no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }                                         

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String nuevoNombre = nuevoNombreField.getText().trim();

        // PRC-56: Validación de entrada (nombre no vacío)
        if (nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nuevo nombre no puede estar vacío.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // PRC-54: Diseñar la ventana de confirmación
        int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de cambiar el nombre de:\n'" + cuentaActual.getNombreCliente() + "'\na\n'" + nuevoNombre + "'?",
                "Confirmar Modificación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // PRC-55: Lógica para guardar el nuevo nombre
                GestorCuentas.modificarNombreCuenta(cuentaActual.getId(), nuevoNombre);
                
                JOptionPane.showMessageDialog(this, "Nombre modificado exitosamente.");
                this.dispose(); // Cerrar ventana al terminar
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void limpiarCampos() {
        nombreActualField.setText("");
        saldoField.setText("");
        nuevoNombreField.setText("");
        guardarBtn.setEnabled(false);
        cuentaActual = null;
    }
}