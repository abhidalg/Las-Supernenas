package logica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Taller1Application {
    public static void main(String[] args) {
        SpringApplication.run(Taller1Application.class, args);
        System.out.println(">>> SERVIDOR JAX-RS LEVANTADO EN PUERTO 8081");
    }
}