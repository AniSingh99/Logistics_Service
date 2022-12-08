package com.logistic.parcel.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    @Pointcut(value = "execution(* com.mynt.parcel..*.*.*(..)) " +
            "&& !execution(* com.mynt.parcel.config..*(..))")
    public void allMethods() {
    } // representing all methods in the project

    @Around("allMethods()")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = point.getSignature().getName();
        // to store the method name
        String className = point.getTarget().getClass().getName();
        // to store the classname
        Object[] parameters = point.getArgs();
        String result = mapper.writeValueAsString(parameters);
        // input arguments in the method
        log.info("method invoked: {}, {}() arguments: {}", className, methodName, result);
        final long start = System.currentTimeMillis();
        Object object = point.proceed();
        // method execution
        final long executionTime = System.currentTimeMillis() - start;
        result = mapper.writeValueAsString(object);
        log.info("{} : {}() Response: {} Execution time: {}", className, methodName, result, executionTime);
        return object;
    }
}
