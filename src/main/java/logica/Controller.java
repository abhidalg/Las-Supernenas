package logica;
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
@RestController
@RequestMapping("/Solicitud")
public class Controller {
    /**
     * Instancia del servicio que contiene la lógica de negocio subyacente.
     * Gestiona la creación de tokens y la obtención de datos para el grid.
     */
    private Service service = new Service();

    /**
     * Endpoint que procesa una nueva solicitud para generar un token.
     * Si no se envían datos en el cuerpo de la petición, se inicializa una solicitud vacía.
     *
     * @param usuario El nombre del usuario que realiza la solicitud, recibido como parámetro de consulta (query param).
     * @param datos   El objeto {@link Solicitud} recibido en el cuerpo de la petición (opcional).
     * @return Un objeto {@link SolicitudResponse} que contiene el estado de la operación y el token generado.
     */
    @GetMapping("/Solicitar")
    public SolicitudResponse solicitar(
            @RequestParam("nombreUsuario") String usuario,
            @RequestBody(required = false) Solicitud datos) {
        if (datos == null) {
            datos = new Solicitud();
        }
        return service.devolverToken(usuario, datos);
    }

    /**
     * Endpoint que obtiene y muestra los datos de una cuadrícula (grid) asociados a un token.
     * Carga la información en el modelo para ser representada en la vista.
     *
     * @param token El identificador único de la solicitud a buscar.
     * @param model El modelo de Spring utilizado para pasar atributos a la vista.
     * @return Una cadena de texto que representa el nombre de la vista a renderizar
     *         ({@code "grid"} si tiene éxito, o {@code "error"} si no se encuentran datos).
     */
    @GetMapping("/grid")
    public String mostrarGrid(@RequestParam("tok") int token, Model model) {
        Map<String, Object> datos = service.obtenerDatosGrid(token);
        if (datos == null) {
            model.addAttribute("error", "No se encontró ningún resultado para el token: " + token);
            return "error";
        }
        model.addAttribute("count", datos.get("count"));
        model.addAttribute("colors", datos.get("colors"));
        model.addAttribute("maxTime", datos.get("maxTime"));
        return "grid";
    }
}