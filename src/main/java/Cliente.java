

public class Cliente {

    private String nombre;
    private String dni;
    private String telefono;
    private String email;
    private double importeVentas;

    public Cliente(String nombre, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.importeVentas = 0;
    }

    public String getNombre() {return nombre;}
    public String getDni() {return dni;}
    public String getTelefono() {return telefono;}
    public String getEmail() {return email;}
    public double getImporteVentas() {return importeVentas;}

    public void aumentarImporteVentas(double importe) {
        importeVentas += importe;
    }

    public void modificarNombre(String nombre) {
        this.nombre = nombre;
    }

    public void modificarTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void modificarEmail(String email) {
        this.email = email;
    }

}