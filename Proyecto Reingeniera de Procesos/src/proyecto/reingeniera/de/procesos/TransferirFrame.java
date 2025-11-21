/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import javax.swing.JOptionPane;
import java.awt.Color;

public class TransferirFrame extends javax.swing.JFrame {

    private Cuenta cuentaOrigen = null;
    private boolean destinoVerificado = false;

    // Componentes UI
    private javax.swing.JLabel jLabelTitulo;
    
    // Sección Origen
    private javax.swing.JLabel jLabelOrigen;
    private javax.swing.JTextField idOrigenField;
    private javax.swing.JButton buscarOrigenBtn;
    private javax.swing.JLabel infoOrigenLabel;

    // Sección Destino
    private javax.swing.JLabel jLabelDestino;
    private javax.swing.JTextField idDestinoField;
    private javax.swing.JButton verificarDestinoBtn;
    private javax.swing.JLabel infoDestinoLabel;

    // Sección Monto y Acción
    private javax.swing.JLabel jLabelMonto;
    private javax.swing.JTextField montoField;
    private javax.swing.JButton transferirBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JSeparator jSeparator1;

    public TransferirFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Inicialización básica de componentes
        jLabelTitulo = new javax.swing.JLabel();
        jLabelOrigen = new javax.swing.JLabel();
        idOrigenField = new javax.swing.JTextField();
        buscarOrigenBtn = new javax.swing.JButton();
        infoOrigenLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelDestino = new javax.swing.JLabel();
        idDestinoField = new javax.swing.JTextField();
        verificarDestinoBtn = new javax.swing.JButton();
        infoDestinoLabel = new javax.swing.JLabel();
        jLabelMonto = new javax.swing.JLabel();
        montoField = new javax.swing.JTextField();
        transferirBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transferencia Bancaria");

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Transferir Fondos");

        // --- SECCIÓN ORIGEN ---
        jLabelOrigen.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabelOrigen.setText("ID Cuenta Origen (Tu cuenta):");
        buscarOrigenBtn.setText("Buscar");
        buscarOrigenBtn.addActionListener(evt -> buscarOrigenBtnActionPerformed(evt));
        
        infoOrigenLabel.setText("---");
        infoOrigenLabel.setForeground(Color.GRAY);

        // --- SECCIÓN DESTINO ---
        jLabelDestino.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabelDestino.setText("ID Cuenta Destino:");
        verificarDestinoBtn.setText("Verificar");
        verificarDestinoBtn.setEnabled(false); // Deshabilitado hasta tener origen
        verificarDestinoBtn.addActionListener(evt -> verificarDestinoBtnActionPerformed(evt));
        
        infoDestinoLabel.setText("---");
        infoDestinoLabel.setForeground(Color.GRAY);

        // --- SECCIÓN MONTO ---
        jLabelMonto.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabelMonto.setText("Monto a Transferir ($):");

        transferirBtn.setFont(new java.awt.Font("Segoe UI", 1, 14));
        transferirBtn.setText("Realizar Transferencia");
        transferirBtn.setEnabled(false); // Deshabilitado hasta verificar todo
        transferirBtn.addActionListener(evt -> transferirBtnActionPerformed(evt));

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(evt -> this.dispose());

        // --- LAYOUT MANUAL (Simplificado para copiar/pegar) ---
        // Nota: Si usas el diseñador de NetBeans, solo arrastra los elementos.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1)
                .addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelOrigen).addComponent(jLabelDestino).addComponent(jLabelMonto))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addComponent(idOrigenField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(buscarOrigenBtn))
                        .addGroup(layout.createSequentialGroup().addComponent(idDestinoField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(verificarDestinoBtn))
                        .addComponent(montoField)))
                .addComponent(infoOrigenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(infoDestinoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup().addComponent(cancelarBtn).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE).addComponent(transferirBtn)))
            .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(15, 15, 15)
                .addComponent(jLabelTitulo).addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelOrigen).addComponent(idOrigenField).addComponent(buscarOrigenBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(infoOrigenLabel).addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelDestino).addComponent(idDestinoField).addComponent(verificarDestinoBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(infoDestinoLabel).addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelMonto).addComponent(montoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(transferirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
    }

    // --- LÓGICA DE NEGOCIO ---

    private void buscarOrigenBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String id = idOrigenField.getText().trim();
        if(id.isEmpty()) return;

        cuentaOrigen = GestorCuentas.buscarCuenta(id);

        if (cuentaOrigen != null) {
            infoOrigenLabel.setText("Origen: " + cuentaOrigen.getNombreCliente() + " | Saldo: $" + cuentaOrigen.getSaldo());
            infoOrigenLabel.setForeground(new Color(0, 100, 0)); // Verde oscuro
            // Habilitamos el siguiente paso
            verificarDestinoBtn.setEnabled(true);
            idOrigenField.setEditable(false); // Bloqueamos para que no lo cambie a media operación
            buscarOrigenBtn.setEnabled(false);
        } else {
            infoOrigenLabel.setText("Cuenta no encontrada");
            infoOrigenLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "Cuenta origen no encontrada.");
        }
    }

    private void verificarDestinoBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String idDest = idDestinoField.getText().trim();
        if(idDest.isEmpty()) return;
        
        // Validación básica UI: Origen != Destino
        if(cuentaOrigen.getId().equals(idDest)){
            JOptionPane.showMessageDialog(this, "No puedes transferir a la misma cuenta.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Buscamos solo para verificar que existe (PRC-74)
        Cuenta cDest = GestorCuentas.buscarCuenta(idDest);

        if (cDest != null) {
            infoDestinoLabel.setText("Destinatario: " + cDest.getNombreCliente());
            infoDestinoLabel.setForeground(new Color(0, 100, 0));
            // Todo listo, habilitamos transferencia
            destinoVerificado = true;
            transferirBtn.setEnabled(true);
            idDestinoField.setEditable(false);
            verificarDestinoBtn.setEnabled(false);
        } else {
            // PRC-75: Mensaje Cuenta destino no encontrada
            infoDestinoLabel.setText("Cuenta destino no existe");
            infoDestinoLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, "La cuenta destino no existe en el sistema.");
        }
    }

    private void transferirBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (!destinoVerificado || cuentaOrigen == null) return;

        String montoStr = montoField.getText().trim();
        try {
            // PRC-76: Validación de entrada (cantidad)
            double monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser positivo.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Llamada al Gestor (PRC-77)
            GestorCuentas.transferir(cuentaOrigen.getId(), idDestinoField.getText().trim(), monto);

            // PRC-78: Mensaje de 'Transferencia exitosa'
            JOptionPane.showMessageDialog(this, "¡Transferencia realizada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Aquí capturamos Saldo Insuficiente (PRC-73) o errores de destino
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Fallo en Transferencia", JOptionPane.ERROR_MESSAGE);
        }
    }
}