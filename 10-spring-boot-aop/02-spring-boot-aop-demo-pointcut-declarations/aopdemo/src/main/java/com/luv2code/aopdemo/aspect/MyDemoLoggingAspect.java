package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
// @Order(1)
// diğer aspectlerden önce çalışması için @Order(1) kullanılır
public class MyDemoLoggingAspect {

    // iki expressionu birleştirmek için operatörler kullanılır
    // &&, ||, !
    // @Before("forDaoPackage() && !forGetterAndSetter()")
    // @Before("forDaoPackage() || forGetterAndSetter()")

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
    }


    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // eğer istersek result içerisindeki verileri burada değiştirebiliriz

        System.out.println("\n=====>>> result is: " + result);
    }


    // add a new advice for @AfterThrowing on the findAccounts method
    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exception")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        System.out.println("\n=====>>> The exception is: " + exception);
    }

    // add a new advice for @After on the findAccounts method
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

    // @Around advice
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortuneAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println("@Around advice: We have a problem: " + e.getMessage());
            result = "Nothing special here. Move along!";
            throw e; // hatayı main methoda fırlatır
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;

        System.out.println("\n=====>>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }
}
