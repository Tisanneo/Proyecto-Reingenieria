package proyecto.reingeniera.de.procesos;

import java.io.Serializable;
import java.util.ArrayList; // Importar esto

public class Cuenta implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String id;
    private String nombreCliente;
    private double saldo;
    
    // NUEVO: Lista para guardar el historial (PRC-116)
    private ArrayList<String> historialTransacciones;

    public Cuenta(String id, String nombreCliente, double saldo) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.saldo = saldo;
        // Inicializamos la lista vacía
        this.historialTransacciones = new ArrayList<>();
        // Registramos la creación como primera transacción
        registrarTransaccion("Apertura de cuenta: +$" + saldo);
    }

    // --- Getters y Setters existentes ---
    public String getId() { return id; }
    public String getNombreCliente() { return nombreCliente; }
    public double getSaldo() { return saldo; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    // --- NUEVOS MÉTODOS PARA HISTORIAL ---
    
    public ArrayList<String> getHistorial() {
        return historialTransacciones;
    }

    public void registrarTransaccion(String descripcion) {
        this.historialTransacciones.add(descripcion);
    }
}