package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO,
											   TrafficFortuneService trafficFortuneService){
		return runner -> {
			//demoTheBeforeAdvice(accountDAO,membershipDAO);
			//demoTheAfterReturningAdvice(accountDAO);
			//demoTheAfterThrowingAdvice(accountDAO);
			//demoTheAfterAdvice(accountDAO);
			demoTheAroundAdvice(trafficFortuneService);
		};
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("\n\nMain Program: AroundDemoApp");
		System.out.println("Calling getFortune");

		String data = trafficFortuneService.getFortune();

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");
		System.out.println("\n");
	}

	private void demoTheAfterAdvice(AccountDAO accountDAO) {
		List<Account> accounts = null;

		try{
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;
			accounts = accountDAO.findAccounts(tripWire);
		}catch (Exception e){
			System.out.println("\n\nMain Program: Caught exception: " + e);
		}

		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(accounts);

		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
		List<Account> accounts = null;

		try{
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);
		}catch (Exception e){
			System.out.println("\n\nMain Program: Caught exception: " + e);
		}

		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(accounts);

		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {
		List<Account> accounts = accountDAO.findAccounts();

		System.out.println("\n\nMain Program: AfterReturningDemoApp");
		System.out.println("----");
		System.out.println(accounts);

		System.out.println("\n");

	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		Account account = new Account();


		accountDAO.addAccount(account, true);

		// membershipDAO aspectedeki pointcut expression ile eşleşmediği için aspect çalışmayacak
		membershipDAO.addAccount();
	}

}
