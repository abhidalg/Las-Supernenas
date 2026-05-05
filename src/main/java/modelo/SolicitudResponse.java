package modelo;

/**
 * Modelo de respuesta estándar tras una solicitud de token exitosa.
 * Incluye el identificador único (token) para futuras consultas, así como
 * información sobre el estado de la operación y posibles errores.
 */
public class SolicitudResponse {
    /**
     * Indica si la solicitud se completó correctamente.
     */
    private boolean done;

    /**
     * Identificador único (token) generado para la operación.
     */
    private int tokenSolicitud;

    /**
     * Mensaje de error detallado en caso de que la operación haya fallado.
     */
    private String errorMessage;

    /**
     * Indicador booleano que representa el estado o la disponibilidad de los datos asociados.
     */
    private boolean data;

    /**
     * Constructor parametrizado de la clase SolicitudResponse.
     * Inicializa una nueva respuesta con todos los detalles de la operación.
     *
     * @param done           Indica si la operación fue exitosa.
     * @param tokenSolicitud El identificador único generado.
     * @param errorMessage   El mensaje de error si ocurrió algún fallo.
     * @param data           El indicador del estado de los datos.
     */
    public SolicitudResponse(boolean done, int tokenSolicitud, String errorMessage, boolean data) {
        this.done = done;
        this.tokenSolicitud = tokenSolicitud;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    /**
     * Constructor por defecto de la clase SolicitudResponse.
     * Crea una instancia vacía sin inicializar los atributos.
     */
    public SolicitudResponse() {

    }

    /**
     * Comprueba si la operación se completó correctamente.
     *
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     */
    public boolean isDone() { return done; }

    /**
     * Establece el estado de finalización de la operación.
     *
     * @param done Define si la operación se completó correctamente.
     */
    public void setDone(boolean done) { this.done = done; }

    /**
     * Obtiene el token de la solicitud.
     *
     * @return El identificador único generado para la operación.
     */
    public int getTokenSolicitud() { return tokenSolicitud; }

    /**
     * Establece el identificador único (token) para la operación.
     *
     * @param tokenSolicitud El número entero que servirá como token.
     */
    public void setTokenSolicitud(int tokenSolicitud) { this.tokenSolicitud = tokenSolicitud; }

    /**
     * Obtiene el mensaje de error asociado a la respuesta, si lo hay.
     *
     * @return Un {@link String} con el mensaje de error.
     */
    public String getErrorMessage() { return errorMessage; }

    /**
     * Establece un mensaje de error para esta respuesta.
     *
     * @param errorMessage El mensaje de error descriptivo a asignar.
     */
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    /**
     * Comprueba el estado o disponibilidad de los datos.
     *
     * @return {@code true} si el estado de los datos es positivo, {@code false} en caso contrario.
     */
    public boolean isData() { return data; }

    /**
     * Establece el indicador de estado o disponibilidad de los datos.
     *
     * @param data El valor booleano a asignar a los datos.
     */
    public void setData(boolean data) { this.data = data; }
    }
