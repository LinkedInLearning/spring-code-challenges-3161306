package com.cecilireid.springchallenges;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.cecilireid.springchallenges.CateringJobController.*ById(Long)) && args(id)")
    public Object logObjects(ProceedingJoinPoint proceedingJoinPoint, Long id) throws Throwable {
        logger.info("Received request to get job by id={}", id);
        Object response = proceedingJoinPoint.proceed();
        logger.info("Returned response for request: {}", response);
        return response;
    }
}
