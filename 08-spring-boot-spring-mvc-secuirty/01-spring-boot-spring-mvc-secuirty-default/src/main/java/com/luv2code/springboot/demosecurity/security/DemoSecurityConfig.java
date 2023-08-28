package com.luv2code.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC authentication
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        /*
        Spring Security'nin default tablo isimlerini kullanmak istemiyorsak
        aşağıdaki gibi tablo isimlerini değiştirebiliriz.
        tablo isimleri değiştirilirse aşağıdaki sorguları da değiştirmek gerekir.

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from users where username=?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username=?"
        );
        return jdbcUserDetailsManager;
        */

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE") //  / kök dizinine istek gelirse kimlik doğrulama yap
                        .requestMatchers("/leaders/**").hasRole("MANAGER") // /leaders/** isteği için kimlik doğrulama yap
                        .requestMatchers("/systems/**").hasRole("ADMIN") // /systems/** isteği için kimlik doğrulama yap
                        .anyRequest().authenticated() // her istek için kimlik doğrulama yap
        )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage") // login sayfası
                                .loginProcessingUrl("/authenticateTheUser") // login formunun actionı (login sayfasında) th:action="${authenticateTheUser}"
                                .permitAll() // herkese açık
                        )
                .logout(logout ->logout.permitAll() // /logout ile çıkış yapılır
                )
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied") // erişim reddedilirse /access-denied sayfasına yönlendir
                );
                return http.build();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
            .username("john")
            .password("{noop}test123")
            .roles("EMPLOYEE")
            .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john,mary,susan);
    } */
}
