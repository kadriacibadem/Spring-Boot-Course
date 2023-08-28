package com.kadri.config;

import com.kadri.Coach;
import com.kadri.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean
    // @Bean(name="aquatic") gibi bean id tanımlayıp controllerdaki qualifiere
    // bu adı verebiliriz.
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
