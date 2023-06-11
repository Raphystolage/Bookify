package hr.algebra.bookify.webapp;

import io.prometheus.client.exporter.HTTPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WebAppApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WebAppApplication.class, args);
    }

}
