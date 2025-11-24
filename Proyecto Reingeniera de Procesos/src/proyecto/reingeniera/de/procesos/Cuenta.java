package proyecto.reingeniera.de.procesos;

import java.io.Serializable;
import java.util.ArrayList; 

public class Cuenta implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String id;
    private String nombreCliente;
    private double saldo;
    
    private ArrayList<String> historialTransacciones;

    public Cuenta(String id, String nombreCliente, double saldo) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.saldo = saldo;
        this.historialTransacciones = new ArrayList<>();
        registrarTransaccion("Apertura de cuenta: +$" + saldo);
    }

    public String getId() { return id; }
    public String getNombreCliente() { return nombreCliente; }
    public double getSaldo() { return saldo; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public ArrayList<String> getHistorial() {
        return historialTransacciones;
    }

    public void registrarTransaccion(String descripcion) {
        this.historialTransacciones.add(descripcion);
    }
}