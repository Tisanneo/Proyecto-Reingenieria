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

public class Retirardinero{

    public static void retirar(){

        String id = JOptionPane.showInputDialog(null,"INGRESE ID PARA RETIRAR:","RETIRO",JOptionPane.QUESTION_MESSAGE);

        if(id==null || id.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"CANCELADO","RETIRO",JOptionPane.WARNING_MESSAGE);
            return;
        }

        id =id.trim();
        Cuenta cuenta = GestorCuentas.buscarCuenta(id);

        if(cuenta == null){
            JOptionPane.showMessageDialog(null,"CUENTA "+id+" NO EXISTE","Err",JOptionPane.ERROR_MESSAGE);
            return;
        }
        String montoStr = JOptionPane.showInputDialog(null,"TITULAR: "+cuenta.getNombreCliente()+"\nSALDO: $"+cuenta.getSaldo()+"\n\nMONTO A RETIRAR:","RETIRO",JOptionPane.QUESTION_MESSAGE);

        if (montoStr == null) {
            JOptionPane.showMessageDialog(null,"RETIRO CANCELADO",  "AVISO",JOptionPane.WARNING_MESSAGE);
            return;
        }

        double monto;
        try{
            monto = Double.parseDouble(montoStr);
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(null,"MONTO INVALIDO","Err",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(monto <= 0){
            JOptionPane.showMessageDialog(null,"MONTO DEBE SER > 0","Err",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (monto > cuenta.getSaldo()) {
            JOptionPane.showMessageDialog(null,"SALDO INSUFICIENTE\nDISONIBLE: $"+cuenta.getSaldo(),"Err SALDO",JOptionPane.ERROR_MESSAGE);
            return;
        }
        cuenta.setSaldo(cuenta.getSaldo() -monto);
        try{
            GestorCuentas.guardarCuentas(GestorCuentas.cargarCuentas());
        }catch(IOException y){
            JOptionPane.showMessageDialog(null,"FALLO AL GUARDAR","Err",JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null,"RETIRO EXITOSO\n\nTITULAR: "+cuenta.getNombreCliente()+"\nID: "+id+"\nRETIRO: $"+monto+"\nNUEVO SALDO: $"+cuenta.getSaldo(),"EXITO",JOptionPane.INFORMATION_MESSAGE);
   }}
