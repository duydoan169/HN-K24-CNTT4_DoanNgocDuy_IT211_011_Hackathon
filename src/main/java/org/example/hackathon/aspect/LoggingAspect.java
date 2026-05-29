package org.example.hackathon.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(* org.example.hackathon.service.BookService.createBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.updateBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.patchBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.deleteBook())")
    public void beforeLogger(JoinPoint joinPoint){
        log.info("Tên phương thức: {}, tham số: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterThrowing("execution(* org.example.hackathon.service.BookService.createBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.updateBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.patchBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.deleteBook())")
    public void afterThrowingLogger(JoinPoint joinPoint){
        log.warn("Phương thức: {} thất bại", joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* org.example.hackathon.service.BookService.createBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.updateBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.patchBook()) ||" +
            "execution(* org.example.hackathon.service.BookService.deleteBook())")
    public void afterReturningLogger(JoinPoint joinPoint){
        log.info("Phương thức: {} thành công", joinPoint.getSignature().getName());
    }
}
