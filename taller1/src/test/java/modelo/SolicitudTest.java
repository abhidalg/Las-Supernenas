/*package modelo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudTest {
    @Test
    void testConstructorYGetters() {
        List<Integer> cantidades = Arrays.asList(1, 2, 3);
        List<String> entidades = Arrays.asList("Rojo", "Verde", "Azul");

        Solicitud solicitud = new Solicitud(cantidades, entidades);

        assertEquals(cantidades, solicitud.getCantidadesIniciales(), "Las cantidades iniciales no coinciden");
        assertEquals(entidades, solicitud.getNombreEntidades(), "Los nombres de entidades no coinciden");
    }

    @Test
    void testSetters() {
        Solicitud solicitud = new Solicitud(null, null);

        List<Integer> nuevasCantidades = Arrays.asList(5, 10);
        List<String> nuevasEntidades = Arrays.asList("Amarillo", "Rosa");

        solicitud.setCantidadesIniciales(nuevasCantidades);
        solicitud.setNombreEntidades(nuevasEntidades);

        assertEquals(nuevasCantidades, solicitud.getCantidadesIniciales());
        assertEquals(nuevasEntidades, solicitud.getNombreEntidades());
    }
}*/