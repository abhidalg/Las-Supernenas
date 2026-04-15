package modelo;

public class SolicitudResponse {
    private boolean done;
    private int tokenSolicitud;
    private String errorMessage;
    private boolean data;
    public SolicitudResponse(boolean done, int tokenSolicitud, String errorMessage, boolean data) {
        this.done = done;
        this.tokenSolicitud = tokenSolicitud;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public SolicitudResponse() {

    }

    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    public int getTokenSolicitud() { return tokenSolicitud; }
    public void setTokenSolicitud(int tokenSolicitud) { this.tokenSolicitud = tokenSolicitud; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public boolean isData() { return data; }
    public void setData(boolean data) { this.data = data; }
}
