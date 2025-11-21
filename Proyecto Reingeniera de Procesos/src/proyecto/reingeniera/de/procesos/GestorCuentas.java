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
    private static final String ARCHIVO_CERRADAS = "cuentas_cerradas.dat";
    
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
    static synchronized void guardarCuentas(ArrayList<Cuenta> cuentas) throws IOException {
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
/**
     * MODIFICACIÓN CRÍTICA: Actualiza tu método eliminarCuenta existente
     * para que guarde la cuenta antes de borrarla.
     */
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

        // --- NUEVO BLOQUE: GUARDAR EN RESPALDO ANTES DE BORRAR ---
        ArrayList<Cuenta> listaCerradas = cargarCuentasCerradas();
        // Agregamos una nota final al historial
        cuentaAEliminar.registrarTransaccion("CUENTA CERRADA DEFINITIVAMENTE");
        listaCerradas.add(cuentaAEliminar);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CERRADAS))) {
            oos.writeObject(listaCerradas);
        }
        // ---------------------------------------------------------

        // Ahora sí, la borramos de la lista activa
        cuentas.remove(indice);
        
        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar los cambios: " + e.getMessage());
        }
    }
    
    /**
     * NUEVO MÉTODO
     * Modifica el nombre de una cuenta existente y guarda los cambios.
     * (Cumple con PRC-55)
     */
    public static synchronized void modificarNombreCuenta(String id, String nuevoNombre) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        boolean encontrado = false;

        for (Cuenta c : cuentas) {
            if (c.getId().equals(id)) {
                c.setNombreCliente(nuevoNombre); // Aquí aplicamos el cambio
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("No se encontró la cuenta con ID: " + id);
        }

        try {
            guardarCuentas(cuentas); // Guardamos la lista actualizada en el archivo
        } catch (IOException e) {
            throw new Exception("Error al guardar los cambios: " + e.getMessage());
        }
    }
    
    /**
     * NUEVO MÉTODO
     * Realiza un depósito en una cuenta existente.
     * Cumple con PRC-62, 63, 65 y 116.
     */
    public static synchronized void depositar(String id, double monto) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        boolean encontrado = false;

        for (Cuenta c : cuentas) {
            if (c.getId().equals(id)) {
                // PRC-65: Sumar el depósito al saldo
                double nuevoSaldo = c.getSaldo() + monto;
                c.setSaldo(nuevoSaldo);
                
                // PRC-116: Guardar el registro de esta transacción
                c.registrarTransaccion("Depósito: +$" + monto);
                
                encontrado = true;
                break;
            }
        }

        // PRC-63: Mensaje de error si la cuenta no existe
        if (!encontrado) {
            throw new Exception("Cuenta con ID " + id + " no existe.");
        }

        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar la transacción: " + e.getMessage());
        }
    }
    
    /**
     * NUEVO MÉTODO
     * Realiza un retiro de una cuenta existente.
     * Cumple con PRC-39, 40, 50, 117.
     */
    public static synchronized void retirar(String id, double monto) throws Exception {
        ArrayList<Cuenta> cuentas = cargarCuentas();
        boolean encontrado = false;

        for (Cuenta c : cuentas) {
            if (c.getId().equals(id)) {
                
                // PRC-39: Programar la lógica de validación de saldo
                if (monto > c.getSaldo()) {
                    // PRC-40: Programar el mensaje de 'Saldo insuficiente' (lanzamos la excepción)
                    throw new Exception("Saldo insuficiente. Saldo actual: $" + c.getSaldo());
                }
                
                // PRC-50: Programar la lógica para restar el retiro al saldo
                double nuevoSaldo = c.getSaldo() - monto;
                c.setSaldo(nuevoSaldo);
                
                // PRC-117: Programar el guardado del registro de esta transacción
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
    
    /**
     * NUEVO MÉTODO: Transferir
     * Mueve dinero de una cuenta origen a una destino.
     * Cumple con PRC-72, 73, 74, 75, 77 y 118.
     */
    public static synchronized void transferir(String idOrigen, String idDestino, double monto) throws Exception {
        // Validación básica: No puedes transferirte a ti mismo
        if (idOrigen.equals(idDestino)) {
            throw new Exception("No puedes transferir dinero a la misma cuenta.");
        }

        ArrayList<Cuenta> cuentas = cargarCuentas();
        Cuenta cuentaOrigen = null;
        Cuenta cuentaDestino = null;

        // Buscamos ambas cuentas en la lista
        for (Cuenta c : cuentas) {
            if (c.getId().equals(idOrigen)) {
                cuentaOrigen = c;
            } else if (c.getId().equals(idDestino)) {
                cuentaDestino = c;
            }
        }

        // PRC-74 y 75: Validar existencia de cuenta destino
        if (cuentaDestino == null) {
            throw new Exception("La cuenta destino (ID: " + idDestino + ") no existe.");
        }
        
        // (Validación extra por seguridad)
        if (cuentaOrigen == null) {
            throw new Exception("Error interno: La cuenta origen no fue encontrada.");
        }

        // PRC-72 y 73: Validar saldo suficiente en cuenta origen
        if (cuentaOrigen.getSaldo() < monto) {
            throw new Exception("Saldo insuficiente para realizar la transferencia.");
        }

        // PRC-77: Lógica de la transacción (Resta y Suma)
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        // PRC-118: Guardar registro en AMBAS cuentas
        cuentaOrigen.registrarTransaccion("Transferencia enviada a " + idDestino + ": -$" + monto);
        cuentaDestino.registrarTransaccion("Transferencia recibida de " + idOrigen + ": +$" + monto);

        // Guardar cambios en el archivo
        try {
            guardarCuentas(cuentas);
        } catch (IOException e) {
            throw new Exception("Error al guardar la transferencia: " + e.getMessage());
        }
    }
    
    /**
     * NUEVO MÉTODO: Carga la lista de cuentas cerradas desde su archivo.
     */
    public static synchronized ArrayList<Cuenta> cargarCuentasCerradas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_CERRADAS))) {
            cuentas = (ArrayList<Cuenta>) ois.readObject();
        } catch (Exception e) {
            // Si no existe el archivo o está vacío, regresamos lista vacía.
        }
        return cuentas;
    }

    /**
     * NUEVO MÉTODO: Busca una cuenta en el archivo de cerradas (PRC-107, PRC-108)
     */
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