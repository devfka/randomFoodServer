//package com.randomfood.food.logging;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggingAspect
//{
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    private final Environment env;
//
//    public LoggingAspect(Environment env) {
//        this.env = env;
//    }
//
//    /**
//     * Before that matches all repositories, services and Web REST endpoints.
//     */
//    @Before("within(com.randomfood.food.repository.*)"+
//            " || within(com.randomfood.food..service..*)"+
//            " || within(com.randomfood.food..controller.*))")
//    public void applicationPackagePointcutBefore(JoinPoint joinPoint) {
//
//        log.info("Application Logging : There is an operation which is logging on : " + joinPoint);
//    }
//}
