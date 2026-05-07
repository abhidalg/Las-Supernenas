package logica;


import interfaz.SolicitudRepositorio;
import modelo.Solicitud;
import modelo.SolicitudEntidad;
import modelo.SolicitudResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase de servicio encargada de la lógica de negocio del sistema.
 * Gestiona la generación de tokens aleatorios, la simulación de resultados en formato de cuadrícula (grid)
 * y el almacenamiento de las solicitudes tanto en memoria temporal como en la base de datos.
 */
@org.springframework.stereotype.Service
public class SolicitudService {

    /**
     * Repositorio para la persistencia de las entidades de solicitud en la base de datos.
     */
    private final SolicitudRepositorio repositorio;

    /**
     * Generador de números aleatorios para la creación de tokens y datos de la cuadrícula.
     */
    private final Random random = new Random();

    /**
     * Constructor que recibe el repositorio por inyección de dependencias de Spring.
     *
     * @param repositorio El repositorio JPA para persistir las solicitudes.
     */
    public SolicitudService(SolicitudRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Genera un token aleatorio de 8 cifras y prepara la respuesta de la solicitud.
     * Almacena una cadena de resultado simulado asociada al token en la base de datos.
     *
     * @param usuario El nombre del usuario que realiza la petición.
     * @param datos   Los datos técnicos o iniciales de la solicitud.
     * @return Una respuesta estructurada ({@link SolicitudResponse}) con el token de seguimiento y el estado de éxito.
     */
    public SolicitudResponse devolverToken(String usuario, Solicitud datos) {
        int token = 10000000 + random.nextInt(90000000);
        String resultadoSimulado = generarGridAleatorio();

        SolicitudEntidad entidad = new SolicitudEntidad();
        entidad.setNombreUsuario(usuario);
        entidad.setToken(token);
        entidad.setResultadoData(resultadoSimulado);
        entidad.setDone(true);

        repositorio.save(entidad);

        SolicitudResponse respuesta = new SolicitudResponse();
        respuesta.setDone(true);
        respuesta.setTokenSolicitud(token);
        respuesta.setData(true);
        respuesta.setErrorMessage(null);

        return respuesta;
    }

    /**
     * Genera una cadena de texto que simula los resultados de una cuadrícula (grid) aleatoria.
     *
     * @return Un {@link String} que contiene el ancho de la cuadrícula en la primera línea,
     *         seguido de múltiples líneas con el formato {@code t,y,x,color} ordenadas de forma ascendente por el tiempo (t).
     */
    public String generarGridAleatorio() {
        int ancho = 12;
        int framesDeTiempo = 50;
        int puntosTotales = 1900;
        Random r = new Random();
        String[] colores = {"red", "green", "blue", "yellow"};
        String celdas = IntStream.range(0, puntosTotales)
                .mapToObj(i -> {
                    int t = r.nextInt(framesDeTiempo);
                    int y = r.nextInt(ancho);
                    int x = r.nextInt(ancho);
                    String color = colores[r.nextInt(colores.length)];
                    return new Object[]{t, y, x, color};
                })
                .sorted(Comparator.comparingInt(a -> (int) a[0]))
                .map(a -> String.format("%d,%d,%d,%s", a[0], a[1], a[2], a[3]))
                .collect(Collectors.joining("\n"));
        return ancho + "\n" + celdas;
    }

    /**
     * Recupera y procesa los datos de la cuadrícula asociados a un token específico desde la base de datos.
     *
     * @param token El identificador único de la solicitud que contiene los resultados del grid.
     * @return Un {@link Map} que contiene la dimensión ({@code count}), el tiempo máximo ({@code maxTime})
     *         y el mapa de coordenadas a colores ({@code colors}), o {@code null} si no se encuentra el token.
     */
    public Map<String, Object> obtenerDatosGrid(int token) {
        return repositorio.findByToken(token).map(entidad -> {
            String[] lineas = entidad.getResultadoData().split("\n");
            int count = Integer.parseInt(lineas[0].trim());
            int maxTime = 0;

            Map<String, String> colors = new HashMap<>();
            for (int i = 1; i < lineas.length; i++) {
                String linea = lineas[i].trim();
                if (linea.isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length < 4) continue;

                int t = Integer.parseInt(partes[0]);
                int y = Integer.parseInt(partes[1]);
                int x = Integer.parseInt(partes[2]);
                String color = partes[3];

                colors.put(t + "-" + y + "-" + x, color);
                if (t > maxTime) maxTime = t;
            }
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("count", count);
            resultado.put("colors", colors);
            resultado.put("maxTime", maxTime);
            return resultado;

        }).orElse(null);
    }
    public String obtenerGridString(int token) {
        return repositorio.findByToken(token)
                .map(e -> e.getResultadoData())
                .orElse(null);
    }

}