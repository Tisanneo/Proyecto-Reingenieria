/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GestorCuentas {

    private static final String ARCHIVO_CUENTAS = "cuentas.dat";
    private static final String ARCHIVO_CERRADAS = "cuentas_cerradas.dat";
    
    private static final int MAX_CUENTAS = 10;

    public static synchronized ArrayList<Cuenta> cargarCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CUENTAS))) {
            cuentas = (ArrayList<Cuenta>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar cuentas: " + e.getMessage());
            e.printStackTrace();
        }
        return cuentas;
    }

    static synchronized void guardarCuentas(ArrayList<Cuenta> cuentas) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CUENTAS))) {
            oos.writeObject(cuentas);
        }
    }

    public static synchronized String crearNuevaCuenta(String nombre, double saldo) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();

        if (cuentas.size() >= MAX_CUENTAS) {
            throw new Exception("Límite de 10 cuentas alcanzado. No se pueden crear más.");
        }

        String nuevoId;
        boolean idRepetido;
        Random rand = new Random();

        do {
            int idNum = 100000 + rand.nextInt(900000); 
            nuevoId = String.valueOf(idNum);
            
            idRepetido = false;
            for (Cuenta c : cuentas) {
                if (c.getId().equals(nuevoId)) {
                    idRepetido = true;
                    break;
                }
            }
        } while (idRepetido); 
        
        Cuenta nuevaCuenta = new Cuenta(nuevoId, nombre, saldo);
        cuentas.add(nuevaCuenta);

        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar la cuenta en el archivo: " + e.getMessage());
        }

        return nuevoId; 
    }
    
    public static synchronized Cuenta buscarCuenta(String id) {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        for (Cuenta c : cuentas) {
            if (c.getId().equals(id)) {
                return c; 
            }
        }
        return null; 
    }

    public static synchronized void eliminarCuenta(String id) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        
        Cuenta cuentaAEliminar = null;
        int indice = -1;
        
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getId().equals(id)) {
                cuentaAEliminar = cuentas.get(i);
                indice = i;
                break;
            }
        }

        if (cuentaAEliminar == null) {
            throw new Exception("Error: La cuenta con ID " + id + " no fue encontrada.");
        }

        if (cuentaAEliminar.getSaldo() != 0) {
            throw new Exception("La cuenta no se puede cerrar porque tiene un saldo de: $" 
                                + cuentaAEliminar.getSaldo());
        }

        ArrayList<Cuenta> listaCerradas = cargarCuentasCerradas();
        cuentaAEliminar.registrarTransaccion("CUENTA CERRADA DEFINITIVAMENTE");
        listaCerradas.add(cuentaAEliminar);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CERRADAS))) {
            oos.writeObject(listaCerradas);
        }
 
        cuentas.remove(indice);
        
        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar los cambios: " + e.getMessage());
        }
    }
    
    public static synchronized void modificarNombreCuenta(String id, String nuevoNombre) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        boolean encontrado = false;

        for (Cuenta c : cuentas) {
            if (c.getId().equals(id)) {
                c.setNombreCliente(nuevoNombre); 
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("No se encontró la cuenta con ID: " + id);
        }

        try {
            guardarCuentas(cuentas); 
        } catch (IOException e) {
            throw new Exception("Error al guardar los cambios: " + e.getMessage());
        }
    }
    
    public static synchronized void depositar(String id, double monto) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        boolean encontrado = false;

        for (Cuenta c : cuentas) {
            if (c.getId().equals(id)) {
                double nuevoSaldo = c.getSaldo() + monto;
                c.setSaldo(nuevoSaldo);
                
                c.registrarTransaccion("Depósito: +$" + monto);
                
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("Cuenta con ID " + id + " no existe.");
        }

        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar la transacción: " + e.getMessage());
        }
    }
    
    public static synchronized void retirar(String id, double monto) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        boolean encontrado = false;

        for (Cuenta c : cuentas) {
            if (c.getId().equals(id)) {
                
                if (monto > c.getSaldo()) {
                    throw new Exception("Saldo insuficiente. Saldo actual: $" + c.getSaldo());
                }
                
                double nuevoSaldo = c.getSaldo() - monto;
                c.setSaldo(nuevoSaldo);
                
                c.registrarTransaccion("Retiro: -$" + monto);
                
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("Cuenta con ID " + id + " no existe.");
        }

        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar la transacción: " + e.getMessage());
        }
    }
    
    public static synchronized void transferir(String idOrigen, String idDestino, double monto) throws Exception {
        if (idOrigen.equals(idDestino)) {
            throw new Exception("No puedes transferir dinero a la misma cuenta.");
        }

        ArrayList<Cuenta> cuentas = cargarCuentas();
        Cuenta cuentaOrigen = null;
        Cuenta cuentaDestino = null;

        for (Cuenta c : cuentas) {
            if (c.getId().equals(idOrigen)) {
                cuentaOrigen = c;
            } else if (c.getId().equals(idDestino)) {
                cuentaDestino = c;
            }
        }

        if (cuentaDestino == null) {
            throw new Exception("La cuenta destino (ID: " + idDestino + ") no existe.");
        }
        
        if (cuentaOrigen == null) {
            throw new Exception("Error interno: La cuenta origen no fue encontrada.");
        }

        if (cuentaOrigen.getSaldo() < monto) {
            throw new Exception("Saldo insuficiente para realizar la transferencia.");
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        cuentaOrigen.registrarTransaccion("Transferencia enviada a " + idDestino + ": -$" + monto);
        cuentaDestino.registrarTransaccion("Transferencia recibida de " + idOrigen + ": +$" + monto);

        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar la transferencia: " + e.getMessage());
        }
    }
    
    public static synchronized ArrayList<Cuenta> cargarCuentasCerradas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CERRADAS))) {
            cuentas = (ArrayList<Cuenta>) ois.readObject();
        } catch (Exception e) {
        }
        return cuentas;
    }

    public static synchronized Cuenta buscarCuentaCerrada(String id) {
        ArrayList<Cuenta> cerradas = cargarCuentasCerradas();
        for (Cuenta c : cerradas) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

}