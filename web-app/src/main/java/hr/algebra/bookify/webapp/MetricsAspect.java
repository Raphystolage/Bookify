package hr.algebra.bookify.webapp;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Aspect
public class MetricsAspect {

    HTTPServer httpServer = new HTTPServer(8082);

    Counter exportCounter = Counter.build()
            .name("export_counter")
            .help("Total number of export")
            .register();

    Counter requestCounter = Counter.build()
            .name("request_counter")
            .help("Total number of request")
            .register();

    public MetricsAspect() throws IOException {
    }

    @AfterReturning("execution(* hr.algebra.bookify.webapp.BookController.export(..))")
    public void exportCounter() {
        exportCounter.inc();
    }

    @AfterReturning(
            "execution(* hr.algebra.bookify.webapp.BookController.get*(..)) ||" +
            "execution(* hr.algebra.bookify.webapp.BookController.create(..)) ||" +
            "execution(* hr.algebra.bookify.webapp.BookController.update(..)) ||" +
            "execution(* hr.algebra.bookify.webapp.BookController.deleteById(..))"
    )
    public void requestCounter() {
        requestCounter.inc();
    }

}
