

public class Cliente {

    private String nombre;
    private String dni;
    private String telefono;
    private String email;
    private double importeVentas;

    //Constructor
    public Cliente(String nombre, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.importeVentas = 0;
    }

    //Getters
    public String getNombre() {return this.nombre;}
    public String getDni() {return this.dni;}
    public String getTelefono() {return this.telefono;}
    public String getEmail() {return email;}
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