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

public class transferirDinero{

    public static void transferir(){
        String origen = JOptionPane.showInputDialog(null,"ID ORIGEN:","TRANSFERENCIA",JOptionPane.QUESTION_MESSAGE);

        if(origen==null || origen.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"TRANSFERENCIA CANCELADA","INFO",JOptionPane.WARNING_MESSAGE);
            return;
        }

        origen = origen.trim();
        Cuenta cuentaOrigen = GestorCuentas.buscarCuenta(origen);

        if (cuentaOrigen == null) {
            JOptionPane.showMessageDialog(null,"CUENTA ORIGEN "+origen+" NO EXISTE","Err",JOptionPane.ERROR_MESSAGE);
            return;
        }

        String destino = JOptionPane.showInputDialog(null,"ID DESTINO:","DESTINO",JOptionPane.QUESTION_MESSAGE);

        if(destino == null){
            JOptionPane.showMessageDialog(null,"CANCELADO","INF",JOptionPane.WARNING_MESSAGE);
            return;
        }

        destino = destino.trim();

        if(destino.equals(origen)){
            JOptionPane.showMessageDialog(null,"NO TRANSFERIR A MISMA CUENTA","Err",JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cuenta cuentaDestino = GestorCuentas.buscarCuenta(destino);

        String montoStr = JOptionPane.showInputDialog(null,"DE: "+cuentaOrigen.getNombreCliente()+" ($"+cuentaOrigen.getSaldo()+")\nA: "+cuentaDestino.getNombreCliente()+"\n\nMONTO:","TRANSFERIR",JOptionPane.QUESTION_MESSAGE);

        if (montoStr == null) return;

        double monto;
        try{
            monto = Double.parseDouble(montoStr);
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(null,"MONTO NO VALIDO","Err",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(monto <= 0){
            JOptionPane.showMessageDialog(null,"MONTO> 0","Err",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (monto >cuentaOrigen.getSaldo()){
            JOptionPane.showMessageDialog(null,"SIN SALDO SUFICIENTE","Error saldo",JOptionPane.ERROR_MESSAGE);
            return;
        }
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        try{
            GestorCuentas.guardarCuentas(GestorCuentas.cargarCuentas());
        }catch(IOException y){
            JOptionPane.showMessageDialog(null,"ERROR GUARDAR TRANSFERENCIA","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null,"TRANSFERENCIA OK\n\nDE: "+cuentaOrigen.getNombreCliente()+"\nA: "+cuentaDestino.getNombreCliente()+"\nMONTO: $"+monto+"\nSALDO ORIGEN: $"+cuentaOrigen.getSaldo()+"\nSALDO DESTINO: $"+cuentaDestino.getSaldo(),"EXITO",JOptionPane.INFORMATION_MESSAGE);
    }
}