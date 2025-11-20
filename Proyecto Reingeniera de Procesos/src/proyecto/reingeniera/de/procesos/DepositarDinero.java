/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

/**
 *
 * @author ajcd8
 */
import javax.swing.JOptionPane;
import java.io.IOException;

public class DepositarDinero {

    public static void depositar() {

        String id = JOptionPane.showInputDialog(null, "INGRESE EL ID DE LA CUENTA:","DEPOSITO DE DINERO", JOptionPane.QUESTION_MESSAGE);

        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "OPERACION CANCELADA","DEPOSITO",JOptionPane.WARNING_MESSAGE);
            return;
        }

        id = id.trim();

        Cuenta cuenta = GestorCuentas.buscarCuenta(id);

        if (cuenta == null) {
            JOptionPane.showMessageDialog(null, "Nunca existio esa cuenta","Nunca existio esa cuenta",JOptionPane.WARNING_MESSAGE);
            return;
        }

        String montoStr = JOptionPane.showInputDialog(null, "CUENTA ENCONTRADA: "+ cuenta.getNombreCliente() + "\nSALDO ACTUAL: $" + cuenta.getSaldo() + "\n\nINGRESE EL MONTO A DEPOSITAR:", "DEPOSITO - MONTO", JOptionPane.QUESTION_MESSAGE);

        if (montoStr == null) {
            JOptionPane.showMessageDialog(null, "OPERACIONCELADA", "DEPOSITO", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException x) {
            JOptionPane.showMessageDialog(null, "MONTO INVALIDO", "eRROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "EL MONTO DEBE SER MAYOR A 0", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double nuevoSaldo = cuenta.getSaldo() + monto;
        cuenta.setSaldo(nuevoSaldo);

        try {
            GestorCuentas.guardarCuentas(GestorCuentas.cargarCuentas());
        } catch (IOException x) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR EL DEPOSITO", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "DEPOSITO EXITOSO \n\n CLIENTE: " + cuenta.getNombreCliente() + "\nID CUENTA: " + cuenta.getId() + "\nMONTO DEPOSITADO: $" + monto + "\nNUEVO SALDO: $" + nuevoSaldo, "EXITO", JOptionPane.INFORMATION_MESSAGE);
    }
}