/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.reingeniera.de.procesos;

// Importa esta librería para poder guardar el objeto en un archivo
import java.io.Serializable;

public class Cuenta implements Serializable {
    
    // Agregamos el serialVersionUID para la serialización
    private static final long serialVersionUID = 1L;

    private String id;
    private String nombreCliente;
    private double saldo;

    public Cuenta(String id, String nombreCliente, double saldo) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.saldo = saldo;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    // Setters (Necesitarás el de nombre para la función "Modificar Cuenta")
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Puedes agregar un toString() para facilitar las pruebas
    @Override
    public String toString() {
        return "Cuenta{" + "id=" + id + ", nombreCliente=" + nombreCliente + ", saldo=" + saldo + '}';
    }
}