package logica;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Taller1Application {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/Solicitud/Solicitar", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {

                if ("GET".equals(exchange.getRequestMethod())) {

                    String tokenGenerado = "99482731";
                    String respuestaJson = "{\"tokenSolicitud\": \"" + tokenGenerado + "\"}";

                    System.out.println("========================================");
                    System.out.println("  COMUNICACIÓN ESTABLECIDA CON ÉXITO");
                    System.out.println("  TOKEN ENVIADO: " + tokenGenerado);
                    System.out.println("========================================");

                    // Le devolvemos la respuesta al cliente (Swagger/Postman)
                    exchange.sendResponseHeaders(200, respuestaJson.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(respuestaJson.getBytes());
                    os.close();
                }
            }
        });

        // 3. Encendemos el motor
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor NATIVO encendido y escuchando en el puerto 8080...");
    }
}