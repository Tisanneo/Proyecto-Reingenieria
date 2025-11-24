/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import javax.swing.JOptionPane;

public class DepositarFrame extends javax.swing.JFrame {

    private Cuenta cuentaActual = null;

    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton depositarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField montoField;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelInfo; 
    private javax.swing.JLabel jLabelMonto;

    public DepositarFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")           
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jLabelInfo = new javax.swing.JLabel();
        jLabelMonto = new javax.swing.JLabel();
        montoField = new javax.swing.JTextField();
        depositarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Realizar Depósito");

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Depositar Dinero");

        jLabelId.setText("ID Cuenta:");
        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(evt -> buscarBtnActionPerformed(evt));

        jLabelInfo.setFont(new java.awt.Font("Segoe UI", 2, 12)); 
        jLabelInfo.setForeground(new java.awt.Color(0, 102, 204));
        jLabelInfo.setText("Ingrese ID para buscar cuenta...");
        jLabelInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelMonto.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabelMonto.setText("Monto a Depositar ($):");

        depositarBtn.setText("Confirmar Depósito");
        depositarBtn.setEnabled(false); 
        depositarBtn.addActionListener(evt -> depositarBtnActionPerformed(evt));

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(evt -> this.dispose());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelId)
                        .addGap(18, 18, 18)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarBtn))
                    .addComponent(jLabelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelMonto)
                        .addGap(18, 18, 18)
                        .addComponent(montoField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(depositarBtn)))
                .addContainerGap(30, Short.MAX_VALUE))
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
                .addComponent(jLabelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMonto)
                    .addComponent(montoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(depositarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pack();
    }                    

   

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        cuentaActual = GestorCuentas.buscarCuenta(id);

        if (cuentaActual != null) {
            jLabelInfo.setText(" Cliente: " + cuentaActual.getNombreCliente() + " | Saldo: $" + cuentaActual.getSaldo());
            depositarBtn.setEnabled(true); 
        } else {
            jLabelInfo.setText(" Cuenta no encontrada.");
            depositarBtn.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No existe cuenta con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                         

    private void depositarBtnActionPerformed(java.awt.event.ActionEvent evt) {                                             
        String montoStr = montoField.getText().trim();
        
        try {
            double monto = Double.parseDouble(montoStr);
            
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor a 0.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            GestorCuentas.depositar(cuentaActual.getId(), monto);

            JOptionPane.showMessageDialog(this, "¡Depósito realizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad numérica válida.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al depositar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                            
}