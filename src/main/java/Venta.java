
import java.util.ArrayList;


public class Venta {

    private Cliente cliente;
    private ArrayList<Juego> articulosVenta;
    private String fecha;

    public Venta (Cliente cliente, ArrayList<Juego> articulosVenta, String fecha) {
        this.cliente = cliente;
        this.articulosVenta = articulosVenta;
        this.fecha = fecha;
    }

}