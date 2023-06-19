package hr.algebra.bookify.webapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecutionTimeLoggingAspect {

    Logger logger = LoggerFactory.getLogger(ExecutionTimeLoggingAspect.class);

    @Around("execution(* hr.algebra.bookify.webapp.controller.BookController.create(..))")
    public String logCreationExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        String result = (String) joinPoint.proceed();
        long end = System.nanoTime();
        logger.info("Creation execution time : "+(end-start)/1000000000.0+"s");
        return result;
    }

    @Around("execution(* hr.algebra.bookify.webapp.controller.BookController.update(..))")
    public String logUpdateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        String result = (String) joinPoint.proceed();
        long end = System.nanoTime();
        logger.info("Update execution time : "+(end-start)/1000000000.0+"s");
        return result;
    }

    @Around("execution(* hr.algebra.bookify.webapp.controller.BookController.deleteById(..))")
    public String logDeletionExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        String result = (String) joinPoint.proceed();
        long end = System.nanoTime();
        logger.info("Deletion execution time : "+(end-start)/1000000000.0+"s");
        return result;
    }

}
