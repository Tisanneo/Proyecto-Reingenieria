/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GestorCuentas {

    // Nombre del archivo donde se guardarán las cuentas
    private static final String ARCHIVO_CUENTAS = "cuentas.dat";
    
    // Límite de cuentas según el PDF [cite: 19]
    private static final int MAX_CUENTAS = 10;

    /**
     * Carga la lista de cuentas desde el archivo "cuentas.dat".
     * Si el archivo no existe o está vacío, devuelve una lista nueva.
     */
    public static synchronized ArrayList<Cuenta> cargarCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CUENTAS))) {
            cuentas = (ArrayList<Cuenta>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Es normal si es la primera vez que se ejecuta, crea el archivo después.
        } catch (EOFException e) {
            // El archivo está vacío, lo cual es normal al inicio.
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar cuentas: " + e.getMessage());
            e.printStackTrace();
        }
        return cuentas;
    }

    /**
     * Guarda la lista completa de cuentas en el archivo "cuentas.dat".
     */
    private static synchronized void guardarCuentas(ArrayList<Cuenta> cuentas) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CUENTAS))) {
            oos.writeObject(cuentas);
        }
    }

    /**
     * Implementa la lógica de PRC-24, PRC-25, PRC-26 y PRC-28.
     * Crea una nueva cuenta, la guarda y devuelve el ID generado.
     */
    public static synchronized String crearNuevaCuenta(String nombre, double saldo) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();

        // PRC-26: Programar la validación para no admitir más de 10 cuentas. [cite: 19]
        if (cuentas.size() >= MAX_CUENTAS) {
            throw new Exception("Límite de 10 cuentas alcanzado. No se pueden crear más.");
        }

        // PRC-24: Programar la lógica para generar un ID automático.
        // PRC-25: Programar la validación para que el ID no se repita. [cite: 20]
        String nuevoId;
        boolean idRepetido;
        Random rand = new Random();

        do {
            // Genera un ID de 6 dígitos (puedes ajustar el rango)
            int idNum = 100000 + rand.nextInt(900000); 
            nuevoId = String.valueOf(idNum);
            
            idRepetido = false;
            // Revisa si el ID ya existe
            for (Cuenta c : cuentas) {
                if (c.getId().equals(nuevoId)) {
                    idRepetido = true;
                    break;
                }
            }
        } while (idRepetido); // Repite si el ID ya existe

        // Si salimos del bucle, el ID es único y válido.
        Cuenta nuevaCuenta = new Cuenta(nuevoId, nombre, saldo);
        cuentas.add(nuevaCuenta);

        // PRC-28: Programar la lógica para guardar la cuenta nueva.
        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar la cuenta en el archivo: " + e.getMessage());
        }

        return nuevoId; // Devuelve el ID para mostrarlo al usuario
    }
    
    /**
     * NUEVO MÉTODO
     * Busca y devuelve una cuenta por su ID.
     * Retorna null si la cuenta no se encuentra.
     * (Implementa parte de PRC-33)
     */
    public static synchronized Cuenta buscarCuenta(String id) {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        for (Cuenta c : cuentas) {
            if (c.getId().equals(id)) {
                return c; // La encontró
            }
        }
        return null; // No la encontró
    }

    /**
     * NUEVO MÉTODO
     * Implementa la lógica de eliminación (PRC-34, PRC-36, PRC-37).
     * Valida el saldo y, si es cero, elimina la cuenta.
     * Arroja una Excepción si la cuenta tiene saldo o no se encuentra.
     */
    public static synchronized void eliminarCuenta(String id) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        
        Cuenta cuentaAEliminar = null;
        int indice = -1;
        
        // Buscamos la cuenta y su índice
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getId().equals(id)) {
                cuentaAEliminar = cuentas.get(i);
                indice = i;
                break;
            }
        }

        // Si no se encontró (aunque la UI ya validó, es buena práctica)
        if (cuentaAEliminar == null) {
            throw new Exception("Error: La cuenta con ID " + id + " no fue encontrada.");
        }

        // PRC-34: Programar la validación de saldo en ceros.
        if (cuentaAEliminar.getSaldo() != 0) {
            // PRC-36: Programar la lógica para mostrar error (si tiene saldo)
            throw new Exception("La cuenta no se puede cerrar porque tiene un saldo de: $" 
                                + cuentaAEliminar.getSaldo());
        }

        // PRC-37: Programar la lógica de eliminación
        cuentas.remove(indice);
        
        try {
            // Guardamos la lista actualizada (sin la cuenta eliminada)
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar los cambios en el archivo: " + e.getMessage());
        }
    }
}