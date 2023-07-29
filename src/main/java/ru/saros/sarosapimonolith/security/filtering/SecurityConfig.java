package ru.saros.sarosapimonolith.security.filtering;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(
                        authz -> authz
                                .antMatchers(HttpMethod.GET,
                                        "/api/v1/product/**",
                                        "/api/v1/news/**",
                                        "/api/v1/products",
                                        "/api/v1/image/**").permitAll()
                                .antMatchers(HttpMethod.POST,
                                        "/api/v1/auth/register",
                                        "/api/v1/product/create",
                                        "/api/v1/auth/login").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class))
                .build();
    }
}
