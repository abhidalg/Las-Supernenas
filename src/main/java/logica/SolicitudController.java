package logica;
import modelo.ResultsResponse;
import modelo.Solicitud;
import modelo.SolicitudResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Map;

/**
 * Controlador REST que expone los endpoints para la gestión de solicitudes.
 * Se encarga de interceptar las peticiones HTTP del cliente, delegar la lógica
 * de negocio al servicio correspondiente y devolver la respuesta adecuada.
 */
@org.springframework.stereotype.Controller
@RequestMapping
public class SolicitudController {

    /**
     * Instancia del servicio que contiene la lógica de negocio subyacente.
     * Gestiona la creación de tokens y la obtención de datos para el grid.
     */
    private final SolicitudService solicitudService;

    /**
     * Constructor que recibe el servicio por inyección de dependencias de Spring.
     *
     * @param solicitudService El servicio con la lógica de negocio.
     */
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }



    /**
     * Endpoint que obtiene y muestra los datos de una cuadrícula (grid) asociados a un token.
     *
     * @param token El identificador único de la solicitud a buscar.
     //* @param model El modelo de Spring utilizado para pasar atributos a la vista.
     * @return El nombre de la vista a renderizar.
     */
    @ResponseBody
    @GetMapping("/grid")
    public Map<String, Object> mostrarGrid(@RequestParam("tok") int token) {
        return solicitudService.obtenerDatosGrid(token);
    }
    @ResponseBody
    @PostMapping("/Resultados")
    public ResultsResponse obtenerResultados(
            @RequestParam("nombreUsuario") String usuario,
            @RequestParam("tok") int token) {

        String gridData = solicitudService.obtenerGridString(token);
        if (gridData == null) {
            return new ResultsResponse(false, token, null);
        }
        return new ResultsResponse(true, token, gridData);
    }
    @ResponseBody
    @PostMapping("/Solicitud/Solicitar")
    public SolicitudResponse solicitarPost(
            @RequestParam("nombreUsuario") String usuario,
            @RequestBody(required = false) Solicitud datos) {
        if (datos == null) {
            datos = new Solicitud();
        }
        return solicitudService.devolverToken(usuario, datos);
    }
}