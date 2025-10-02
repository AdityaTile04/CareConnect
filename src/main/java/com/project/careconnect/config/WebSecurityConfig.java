package com.project.careconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers( "/api/v1/doctors" ).permitAll()
                            .requestMatchers( "/api/v1/appointment" ).permitAll()
                            .requestMatchers( "/api/v1/patients" ).authenticated();
                }).formLogin( Customizer.withDefaults());

        return httpSecurity.build();
    }

}
