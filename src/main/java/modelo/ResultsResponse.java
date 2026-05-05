package modelo;

/**
 * Clase que representa la respuesta con los resultados de una solicitud procesada.
 * Contiene el estado de la operación, el token identificador de la solicitud
 * y los datos resultantes devueltos por el sistema.
 */
public class ResultsResponse {
    /**
     * Indica si el procesamiento de la solicitud se ha completado con éxito.
     */
    private boolean done;

    /**
     * Identificador único o token asociado a la solicitud original.
     */
    private int tokenSolicitud;

    /**
     * Contiene los datos resultantes de la operación, generalmente representados como texto o JSON.
     */
    private String data;

    /**
     * Constructor por defecto de la clase ResultsResponse.
     * Crea una instancia vacía sin inicializar los atributos.
     */
    public ResultsResponse() {}

    /**
     * Constructor parametrizado de la clase ResultsResponse.
     * Crea una instancia con el estado, el token y los datos especificados.
     *
     * @param done           {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     * @param tokenSolicitud El identificador único asociado a la solicitud.
     * @param data           Los datos resultantes de la operación.
     */
    public ResultsResponse(boolean done, int tokenSolicitud, String data) {
        this.done = done;
        this.tokenSolicitud = tokenSolicitud;
        this.data = data;
    }

    /**
     * Comprueba si el procesamiento de la solicitud se ha completado con éxito.
     *
     * @return {@code true} si la operación ha finalizado correctamente, {@code false} en caso contrario.
     */
    public boolean isDone() { return done; }

    /**
     * Establece el estado de finalización de la operación.
     *
     * @param done {@code true} para indicar que ha finalizado con éxito, {@code false} en caso contrario.
     */
    public void setDone(boolean done) { this.done = done; }

    /**
     * Obtiene el token o identificador de la solicitud asociada a estos resultados.
     *
     * @return Un número entero ({@code int}) que representa el token de la solicitud.
     */
    public int getTokenSolicitud() { return tokenSolicitud; }

    /**
     * Establece el token o identificador de la solicitud asociada.
     *
     * @param tokenSolicitud El número entero que identifica de forma única la solicitud.
     */
    public void setTokenSolicitud(int tokenSolicitud) { this.tokenSolicitud = tokenSolicitud; }

    /**
     * Obtiene los datos resultantes asociados a la respuesta.
     *
     * @return Un {@link String} que contiene los datos de la respuesta.
     */
    public String getData() { return data; }

    /**
     * Establece los datos resultantes para esta respuesta.
     *
     * @param data Una cadena de texto con los datos que se desean incluir en la respuesta.
     */
    public void setData(String data) { this.data = data; }
}
