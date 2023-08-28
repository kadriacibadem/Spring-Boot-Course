package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {


    // execution(public void add*()) matches any method starting with add and returning void
    // execution(* add*()) matches any method starting with add and returning any type

    //@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")

    // @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))") Account ve sonrasındaki parametreler için
    // @Before("execution(* add*(..))" ) herhangi bir parametre için
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
    }
}
