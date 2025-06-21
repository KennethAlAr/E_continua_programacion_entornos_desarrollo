
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void configuracionInicial() {
        cliente = new Cliente("Kenneth", "12345678A", "666123123", "kenneth@email.com");
        cliente.aumentarImporteVentas(100);
    }

    @Test
    public void getNombre() {
        assertEquals("Kenneth", cliente.getNombre());
    }

    @Test
    public void getDni() {
        assertEquals("12345678A", cliente.getDni());
    }

    @Test
    public void getTelefono() {
        assertEquals("666123123", cliente.getTelefono());
    }

    @Test
    public void getEmail() {
        assertEquals("kenneth@email.com", cliente.getEmail());
    }

    @Test
    public void getImporteVentas() {
        assertEquals(100, cliente.getImporteVentas());
    }

    @Test
    public void modificarNombre() {
        String nuevoNombre = "Sara";
        cliente.modificarNombre(nuevoNombre);
        assertEquals("Sara", cliente.getNombre());
    }

    @Test
    public void modificarTelefono() {
        String nuevoTelefono = "666456456";
        cliente.modificarTelefono(nuevoTelefono);
        assertEquals("666456456", cliente.getTelefono());
    }

    @Test
    public void modificarEmail() {
        String nuevoEmail = "sara@email.com";
        cliente.modificarEmail(nuevoEmail);
        assertEquals("sara@email.com", cliente.getEmail());
    }

    @Test
    public void aumentarImporteVentas() {
        int importe = 100;
        cliente.aumentarImporteVentas(100);
        assertEquals(200, cliente.getImporteVentas());
    }

}
