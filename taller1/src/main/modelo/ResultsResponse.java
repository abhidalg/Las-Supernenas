package main.modelo;

public class ResultsResponse {
    private boolean done;
    private int tokenSolicitud;
    private String data;
    public ResultsResponse() {}

    public ResultsResponse(boolean done, int tokenSolicitud, String data) {
        this.done = done;
        this.tokenSolicitud = tokenSolicitud;
        this.data = data;
    }

    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    public int getTokenSolicitud() { return tokenSolicitud; }
    public void setTokenSolicitud(int tokenSolicitud) { this.tokenSolicitud = tokenSolicitud; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
