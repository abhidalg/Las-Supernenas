package modelo;

/**
 * Clase que representa la respuesta o el resultado de una operación relacionada con correos electrónicos.
 * Contiene información sobre si la operación se realizó con éxito y captura
 * cualquier mensaje de error en caso de fallo.
 */
public class EmailResponse {
    /**
     * Indica si la operación del correo electrónico se completó con éxito.
     */
    private boolean done;

    /**
     * Almacena el mensaje de error detallado si la operación falló.
     */
    private String errorMessage;

    /**
     * Constructor por defecto de la clase EmailResponse.
     * Crea una instancia vacía sin inicializar los atributos.
     */
    public EmailResponse() {}

    /**
     * Constructor parametrizado de la clase EmailResponse.
     * Crea una instancia con los valores de estado y mensaje de error especificados.
     *
     * @param done         {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     * @param errorMessage El mensaje descriptivo del error en caso de que {@code done} sea {@code false}.
     */
    public EmailResponse(boolean done, String errorMessage) {
        this.done = done;
        this.errorMessage = errorMessage;
    }

    /**
     * Comprueba si la operación del correo electrónico se completó con éxito.
     *
     * @return {@code true} si la operación fue exitosa, {@code false} si hubo un error.
     */
    public boolean isDone() { return done; }

    /**
     * Establece el estado de éxito o fracaso de la operación del correo electrónico.
     *
     * @param done {@code true} para marcar la operación como exitosa, {@code false} para marcarla como fallida.
     */
    public void setDone(boolean done) { this.done = done; }

    /**
     * Obtiene el mensaje de error asociado a la operación, si existe.
     *
     * @return Un {@link String} que contiene el mensaje de error, o {@code null} si no hubo errores.
     */
    public String getErrorMessage() { return errorMessage; }

    /**
     * Establece un mensaje de error detallado para la respuesta.
     *
     * @param errorMessage El mensaje de error que se desea asociar a esta respuesta.
     */
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}