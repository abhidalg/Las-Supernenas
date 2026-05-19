package logica;

import interfaz.SolicitudRepositorio;
import modelo.Solicitud;
import modelo.SolicitudEntidad;
import modelo.SolicitudResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Servicio de la capa de negocio encargado de gestionar el procesamiento de las solicitudes
 */
@Service
public class SolicitudService {

    private final SolicitudRepositorio repositorio;
    private final Random random = new Random();

    public SolicitudService(SolicitudRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Procesa una nueva solicitud de simulación: genera un token único aleatorio de 8 cifras,
     * ejecuta el algoritmo de rebotes
     *
     * @param usuario El nombre del usuario que realiza la petición de simulación
     * @param datos   Objeto con los parámetros de configuración iniciales de la solicitud
     * @return Un objeto {@link SolicitudResponse} con el token generado y el estado de la operación
     */
    public SolicitudResponse devolverToken(String usuario, Solicitud datos) {
        int token = 10000000 + random.nextInt(90000000);
        String resultadoSimulado = generarEstelaRebotes();

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
     * Obtiene y procesa las métricas de la simulación para construir las propiedades del Grid.
     * filtra, transforma y recolecta las líneas de la estela en estructuras mapeadas de coordenadas
     *
     * @param token El identificador único numérico de la solicitud a consultar.
     * @return Un {@link Map} que contiene el conteo de líneas, el mapa de coordenadas con sus colores y el tiempo máximo
     */
    public Map<String, Object> obtenerDatosGrid(int token) {
        String datosGuardados = generarEstelaRebotes();
        String[] lineas = datosGuardados.split("\n");
        int count = Integer.parseInt(lineas[0].trim());

        Map<String, String> colors = Arrays.stream(lineas)
                .skip(1)
                .map(String::trim)
                .filter(l -> !l.isEmpty())
                .map(l -> l.split(","))
                .filter(p -> p.length >= 4)
                .collect(Collectors.toMap(
                        p -> p[0] + "-" + p[1] + "-" + p[2],
                        p -> p[3],
                        (existente, nuevo) -> nuevo
                ));

        int maxTime = Arrays.stream(lineas)
                .skip(1)
                .map(l -> l.split(","))
                .filter(p -> p.length >= 4)
                .mapToInt(p -> Integer.parseInt(p[0]))
                .max().orElse(0);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("count", count);
        resultado.put("colors", colors);
        resultado.put("maxTime", maxTime);

        return resultado;
    }
    /**
     * Recupera la secuencia completa e histórica de la simulación de rebotes en formato de texto
     *
     * @param token El identificador único numérico de la solicitud
     * @return Una cadena de texto multilínea que representa la estela de la simulación
     */
    public String obtenerGridString(int token) {
        return generarEstelaRebotes();
    }
    /**
     * Ejecuta el algoritmo principal de la simulación de movimiento de 4 partículas de colores
     * Controla de manera iterativa los desplazamientos de las partículas dentro de un espacio de 12x12
     *
     * @return Una cadena de texto formateada donde la primera línea indica el ancho del tablero,
     * seguido de las trazas de cada partícula.
     */
    private String generarEstelaRebotes() {
        int ancho = 12;
        int frames = 51;
        String[] colores = {"red", "green", "purple", "yellow"};

        int[][] pos = {{0, 1}, {0, ancho - 2}, {ancho - 1, 2}, {ancho - 1, ancho - 3}};
        int[][] dir = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        Map<String, String> gridAcumulado = new HashMap<>();
        List<String> celdas = new ArrayList<>();

        for (int c = 0; c < 4; c++) {
            gridAcumulado.put(pos[c][0] + "_" + pos[c][1], colores[c]);
        }

        celdas.addAll(gridAcumulado.entrySet().stream()
                .map(e -> "0," + e.getKey().replace("_", ",") + "," + e.getValue())
                .toList());

        for (int t = 1; t < frames; t++) {
            int[][] intentoPos = new int[4][2];
            for (int c = 0; c < 4; c++) {
                intentoPos[c][0] = pos[c][0] + dir[c][0];
                intentoPos[c][1] = pos[c][1] + dir[c][1];
            }

            for (int c = 0; c < 4; c++) {
                boolean huboChoque = false;

                if (intentoPos[c][0] < 0 || intentoPos[c][0] >= ancho ||
                        intentoPos[c][1] < 0 || intentoPos[c][1] >= ancho) {
                    huboChoque = true;
                }
                for (int o = 0; o < 4; o++) {
                    if (c == o) continue;
                    boolean directo = (intentoPos[c][0] == intentoPos[o][0] && intentoPos[c][1] == intentoPos[o][1]);
                    boolean cruzado = (intentoPos[c][0] == pos[o][0] && intentoPos[c][1] == pos[o][1] &&
                            intentoPos[o][0] == pos[c][0] && intentoPos[o][1] == pos[c][1]);
                    if (directo || cruzado) huboChoque = true;
                }

                if (huboChoque) {
                    int nuevaSuerte = random.nextInt(3);

                    if (nuevaSuerte == 0) { // Se vuelve Diagonal
                        dir[c][0] = random.nextBoolean() ? 1 : -1;
                        dir[c][1] = random.nextBoolean() ? 1 : -1;
                    } else if (nuevaSuerte == 1) { // Se vuelve Horizontal puro
                        dir[c][0] = 0;
                        dir[c][1] = random.nextBoolean() ? 1 : -1;
                    } else { // Se vuelve Vertical puro
                        dir[c][0] = random.nextBoolean() ? 1 : -1;
                        dir[c][1] = 0;
                    }
                } else {
                    pos[c][0] = intentoPos[c][0];
                    pos[c][1] = intentoPos[c][1];
                }

                gridAcumulado.put(pos[c][0] + "_" + pos[c][1], colores[c]);
            }

            final int tiempoActual = t;
            celdas.addAll(gridAcumulado.entrySet().stream()
                    .map(e -> tiempoActual + "," + e.getKey().replace("_", ",") + "," + e.getValue())
                    .toList());
        }

        imprimirResultadosFinales(gridAcumulado);

        return ancho + "\n" + String.join("\n", celdas);
    }
    /**
     * Agrupa y contabiliza las casillas totales conquistadas por cada color al final de la simulación,
     *
     * @param grid El mapa que contiene las posiciones y colores del tablero
     */
    private void imprimirResultadosFinales(Map<String, String> grid) {
        Map<String, Long> puntos = grid.values().stream()
                .collect(java.util.stream.Collectors.groupingBy(c -> c, java.util.stream.Collectors.counting()));

        System.out.println("\n🏁 --- PUNTUACIÓN FINAL (FRAME 50) ---");
        puntos.forEach((col, val) -> System.out.println(col.toUpperCase() + ": " + val + " casillas"));
        String win = puntos.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("🏆 GANADOR: " + win.toUpperCase() + "\n");
    }
}