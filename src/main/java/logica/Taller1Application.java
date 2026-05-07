package logica;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que actúa como punto de entrada para la aplicación Spring Boot.
 * La anotación {@link SpringBootApplication} habilita la configuración automática,
 * el escaneo de componentes y la configuración de propiedades del framework Spring.
 */
@SpringBootApplication(scanBasePackages = {"logica", "interfaz", "modelo"})
@org.springframework.data.jpa.repository.config.EnableJpaRepositories(basePackages = "interfaz")
@org.springframework.boot.autoconfigure.domain.EntityScan(basePackages = "modelo")

public class Taller1Application {
    /**
     * Método principal (main) que arranca la aplicación.
     * Inicia el contexto de Spring, despliega el servidor web integrado
     * y deja la aplicación lista para escuchar peticiones HTTP.
     *
     * @param args Argumentos de línea de comandos pasados durante el inicio de la aplicación.
     */

        public static void main(String[] args) {
            SpringApplication.run(Taller1Application.class, args);
            System.out.println("Servidor encendido y esperando peticiones...");
        }
}