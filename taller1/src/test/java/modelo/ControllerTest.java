import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Solicitud;
import modelo.SolicitudResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Service service; // Simulamos el servicio para no ejecutar su código real

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    @Test
    public void solicitar_ConDatosValidos_Retorna201() throws Exception {
        // 1. Preparar los datos de entrada
        Solicitud solicitud = new Solicitud(
                Arrays.asList(0),
                Arrays.asList("Entidad1")
        );
        String jsonSolicitud = objectMapper.writeValueAsString(solicitud);

        // 2. Preparar la respuesta simulada del servicio
        SolicitudResponse respuestaSimulada = new SolicitudResponse(true, 12345678, null, true);

        Mockito.when(service.devolverToken(Mockito.eq("pepe"), Mockito.any(Solicitud.class)))
                .thenReturn(respuestaSimulada);

        // 3. Ejecutar la petición HTTP simulada y verificar resultados
        mockMvc.perform(post("/Solicitud/Solicitar")
                        .param("nombreUsuario", "pepe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSolicitud))
                .andExpect(status().isCreated()) // Verifica el 201 Created
                .andExpect(jsonPath("$.done").value(true))
                .andExpect(jsonPath("$.tokenSolicitud").value(12345678))
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    public void solicitar_FaltaNombreUsuario_Retorna400() throws Exception {
        // 1. Preparar los datos de entrada (JSON válido)
        Solicitud solicitud = new Solicitud(
                Arrays.asList(0),
                Arrays.asList("Entidad1")
        );
        String jsonSolicitud = objectMapper.writeValueAsString(solicitud);

        // 2. Ejecutar petición SIN el parámetro "nombreUsuario" (requerido por @RequestParam)
        mockMvc.perform(post("/Solicitud/Solicitar")
                        // Nota: Omitimos deliberadamente el .param("nombreUsuario", ...)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSolicitud))
                .andExpect(status().isBadRequest()); // Verifica el 400 Bad Request automático de Spring
    }

    @Test
    public void solicitar_BodyInvalido_Retorna400() throws Exception {
        // 1. Preparar un JSON malformado
        String jsonMalformado = "{ \"cantidadesIniciales\": \"esto no es un array\" }";

        // 2. Ejecutar petición CON usuario pero con JSON roto
        mockMvc.perform(post("/Solicitud/Solicitar")
                        .param("nombreUsuario", "pepe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMalformado))
                .andExpect(status().isBadRequest()); // Verifica el 400 Bad Request
    }
}
