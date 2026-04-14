package logica;

import modelo.Solicitud;
import modelo.SolicitudResponse;

import javax.ws.rs.*;

@Path("/")
public class Controller {
    @POST
    @Path("/Solicitud/Solicitar")
    @Consumes("application/json")
    @Produces("application/json")
    public SolicitudResponse solicitar(@QueryParam("nombreUsuario") String usuario, Solicitud datos) {
        return new SolicitudResponse(true, 123456789, null, true);
    }
}
