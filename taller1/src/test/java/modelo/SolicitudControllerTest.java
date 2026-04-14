package modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Solicitud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SolicitudController.class)
public class SolicitudControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos Java a JSON

    @Test
    void testEndpointSolicitar_DeberiaDevolverTokenYStatus200() throws Exception {
        // 1. Preparamos el cuerpo de la petición (JSON)
        Solicitud peticion = new Solicitud(
                Arrays.asList(1, 2),
                Arrays.asList("Entidad1", "Entidad2")
        );
        String jsonPeticion = objectMapper.writeValueAsString(peticion);

        // 2. Simulamos el POST a la ruta con el parámetro y el JSON
        mockMvc.perform(post("/Solicitud/Solicitar")
                        .param("nombreUsuario", "Burbuja") // Parámetro de URL
                        .contentType(MediaType.APPLICATION_JSON) // Decimos que enviamos JSON
                        .content(jsonPeticion)) // Metemos el JSON en el body

                // 3. Comprobamos que la respuesta es la esperada según el Swagger y los requisitos
                .andExpect(status().isOk()) // Esperamos un HTTP 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Esperamos que devuelva JSON
                .andExpect(jsonPath("$.done").value(true)) // Comprobamos que el campo 'done' es true
                .andExpect(jsonPath("$.tokenSolicitud").isNumber()); // Comprobamos que genera un token numérico
    }
}
