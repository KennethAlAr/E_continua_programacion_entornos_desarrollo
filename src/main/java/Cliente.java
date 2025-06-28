
/**
 * Representa un cliente de la base de datos de clientes.
 * @author Kenneth Alonso
 * @version 1.0
 */
public class Cliente {

    private String nombre;
    private String dni;
    private String telefono;
    private String email;
    private double importeVentas;

    //Constructor
    /**
     * Constructor de la clase Cliente.
     * @param nombre Nombre del Cliente.
     * @param dni DNI del Cliente.
     * @param telefono Teléfono del Cliente.
     * @param email Correo electrónico del Cliente.
     */
    public Cliente(String nombre, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.importeVentas = 0;
    }

    //Getters
    /**
     * Devuelve el nombre del Cliente.
     * @return Nombre del Cliente.
     */
    public String getNombre() {return this.nombre;}

    /**
     * Devuelve el DNI del Cliente.
     * @return DNI del Cliente.
     */
    public String getDni() {return this.dni;}

    /**
     * Devuelve el nombre del Cliente.
     * @return Nombre del Cliente.
     */
    public String getTelefono() {return this.telefono;}

    /**
     * Devuelve el correo electrónico del Cliente.
     * @return Correo electrónico del Cliente.
     */
    public String getEmail() {return email;}

    /**
     * Devuelve el importe total de las ventas realizadas al Cliente.
     * @return Importe total de las ventas realizadas al Cliente.
     */
    public double getImporteVentas() {return this.importeVentas;}

    //Setters
    /**
     * Método para obtener el total del importe de las ventas realizadas a un cliente.
     * @param importe Importe del total de las ventas realizadas a un cliente.
     */
    public void aumentarImporteVentas(double importe) {
        importeVentas += importe;
    }

    /**
     * Método para modificar el nombre de un cliente.
     * @param nombre Nuevo nombre del cliente.
     */
    public void modificarNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para modificar el teléfono de un cliente.
     * @param telefono Nuevo teléfono del cliente.
     */
    public void modificarTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método para modificar el correo electrónico de un cliente.
     * @param email Nuevo correo electrónico del cliente.
     */
    public void modificarEmail(String email) {
        this.email = email;
    }

}