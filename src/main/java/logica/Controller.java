package logica;
import modelo.Solicitud;
import modelo.SolicitudResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Map;

@RestController
@RequestMapping("/Solicitud")
public class Controller {

    private Service service = new Service();

    @GetMapping("/Solicitar")
    public SolicitudResponse solicitar(
            @RequestParam("nombreUsuario") String usuario,
            @RequestBody(required = false) Solicitud datos) {
        if (datos == null) {
            datos = new Solicitud();
        }
        return service.devolverToken(usuario, datos);
    }
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