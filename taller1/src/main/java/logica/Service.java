package logica;

import modelo.Solicitud;
import modelo.SolicitudResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Service {
    private static Map<Integer, String> memoria = new HashMap<>();
    private Random random = new Random();

    public SolicitudResponse devolverToken(String usuario, Solicitud datos) {
        int token = 10000000 + random.nextInt(90000000);
        String resultadoSimulado = "12\n0,7,4,red";
        memoria.put(token, resultadoSimulado);

        SolicitudResponse respuesta = new SolicitudResponse();
        respuesta.setDone(true);
        respuesta.setTokenSolicitud(token);
        respuesta.setData(true);
        respuesta.setErrorMessage(null);

        return respuesta;
    }
}
