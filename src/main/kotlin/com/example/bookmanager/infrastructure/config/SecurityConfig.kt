package com.example.bookmanager.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests {
                    authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/author/**").permitAll()
                    .requestMatchers("/book/**").authenticated()
                    .anyRequest().authenticated()
            }
            .httpBasic { httpBasic ->
                httpBasic
                    .realmName("BookManager")
            }
        return http.build()
    }

    @Bean
    fun passwordEncorder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
