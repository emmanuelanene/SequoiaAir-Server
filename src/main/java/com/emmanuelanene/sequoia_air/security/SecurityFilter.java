package com.emmanuelanene.sequoia_air.security;

import com.emmanuelanene.sequoia_air.exceptions.CustomAccessDenialHandler;
import com.emmanuelanene.sequoia_air.exceptions.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityFilter {
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Value("${frontend.login.url}")
    private String loginUrl;

    private final AuthFilter authFilter;
    private final CustomAccessDenialHandler customAccessDenialHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable) //to disable _csrf
                .cors(Customizer.withDefaults())
                .exceptionHandling(ex->
                        ex.accessDeniedHandler(customAccessDenialHandler)
                                .authenticationEntryPoint(customAuthenticationEntryPoint))
                .authorizeHttpRequests(req->
                        req.requestMatchers("/api/auth/**", "/v3/api-docs/**", "/api/airports/**", "/api/flights/**", "/swagger-ui/**", "/v3/api-docs")
                                .permitAll()
                                .anyRequest()
                                .authenticated())

                .oauth2Login(oauth2 -> oauth2
                        .loginPage(loginUrl)
                        .successHandler(oAuth2SuccessHandler)
                )

                .logout(logout -> logout
                        .logoutUrl("/signout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setContentType("application/json");
                            response.setStatus(HttpStatus.OK.value());
                            response.getWriter().write("{\"message\": \"You have been logged out successfully\"}");
                        })
                )

                .sessionManagement(mag-> mag.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}








