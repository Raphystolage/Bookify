package hr.algebra.bookify.backend.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestLoggingAspect {

    Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

    @Before("execution (* hr.algebra.bookify.backend.controller.*.get*(..))")
    public void logGetRequest() {
        logger.info("GET request received");
    }

    @Before("execution (* hr.algebra.bookify.backend.controller.*.create*(..))")
    public void logPostRequest() {
        logger.info("POST request received");    }

    @Before("execution (* hr.algebra.bookify.backend.controller.*.update*(..))")
    public void logPutRequest() {
        logger.info("PUT request received");    }

    @Before("execution (* hr.algebra.bookify.backend.controller.*.delete*(..))")
    public void logDeleteRequest() {
        logger.info("DELETE request received");    }

}
