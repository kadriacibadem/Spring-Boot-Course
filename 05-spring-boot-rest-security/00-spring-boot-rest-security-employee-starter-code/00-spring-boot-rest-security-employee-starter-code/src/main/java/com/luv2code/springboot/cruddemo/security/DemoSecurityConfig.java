package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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

    // add support for JDBC .. no more hardcoded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    // yukardakini yazdıktan sonra Spring Security otomatik olarak veritabanındaki
    // kullanıcı rollerini alacak


    /*
    Eğer Spring Security'nin temelde çalıştığı tablo isimlerinden farklı bir tablo ismimiz varsa
    Aşağıdaki şekilde bunu belirtiriz.
    Öncesinde veritabanı içindeki auth ve user tablosunu silip sql-script dosyası içindeki
    06 nolu kodu çalıştırılmalı.

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDeatilsManager = new JdbcUserDetailsManager(dataSource);

        // kullanıcıyı kullanıcı adına göre sorgulayan sql
        jdbcUserDeatilsManager.setUsersByUsernameQuery(
               "select user_id, pw, active from members where user_id=?"
        );

        // kullanıcı adına göre rolü sorgulayan sql
        jdbcUserDeatilsManager.setAuthoritiesByUsernameQuer(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDeatilsManager;
    }
     */



    // rollere göre erişimi kısıtlama
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );
        //use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());
        //disable Cross Site Request Forgery (CSRF)
        //in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        //browser tabanlı uygulamalarda csrf kullanılır
        //non-browser tabanlı uygulamalarda kullanılmasına gerek yok
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

/*
 Bunları veritabanında tuttuğumuz için burada gerek yok
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
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
    }


 */
