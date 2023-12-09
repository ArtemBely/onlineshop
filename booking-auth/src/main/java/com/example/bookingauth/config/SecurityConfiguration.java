package com.example.bookingauth.config;

import com.example.bookingauth.services.JWTAuthentificationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static java.util.Arrays.asList;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/home",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    private final JWTAuthentificationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(asList("http://localhost:3000"));
                    corsConfiguration.setAllowedMethods(asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(asList("Authorization", "Cache-Control", "Content-Type"));
                    corsConfiguration.setAllowCredentials(true);
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", corsConfiguration);
                    cors.configurationSource(source);
                })
//                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(WHITE_LIST_URL).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

//                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout(logout ->
//                        logout.logoutUrl("/api/v1/auth/logout")
//                                .addLogoutHandler(logoutHandler)
//                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//                )
        ;

        return http.build();
    }
}
