package com.example.pharmacy;
import com.example.pharmacy.controllers.JwtRequestFilter;
import com.example.pharmacy.model.User;
import com.example.pharmacy.service.impl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.WebApplicationContext;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Date;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String SECRET_KEY = "secret"; // Ta łańcuch znaków powinien być losowy i bezpieczny.
    private static final long EXPIRATION_TIME = 864_000_000;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    private UserServiceImpl userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    @PostConstruct
    public void completeSetup() {
        userDetailsService = applicationContext.getBean(UserServiceImpl.class);
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/medicines").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/typemedicines").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/api/typemedicines").authenticated()
                        //.requestMatchers(HttpMethod.GET, "/api/typemedicines").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/medicines").hasRole("MODERATOR")
                        .requestMatchers(HttpMethod.POST, "/api/categories").hasRole("MODERATOR")
                        .requestMatchers(HttpMethod.POST, "/api/typemedicines").hasRole("MODERATOR")
                        //.requestMatchers(HttpMethod.PUT, "/api/medicines/**").hasRole("MODERATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/categories").hasRole("MODERATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/typemedicines").hasRole("MODERATOR")

                        .requestMatchers(HttpMethod.PUT, "/api/medicines/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/medicines/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/medicines/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/typemedicines").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/medicines").hasRole("ADMIN")

                        //.requestMatchers("/auth/**").permitAll()
                        .anyRequest().permitAll())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.authenticationEntryPoint(authenticationEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults());

        return http.build();

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public UserDetailsManager users(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
        return jdbcUserDetailsManager;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

@EventListener(ApplicationReadyEvent.class)
public void establishUsers()
    {
    userDetailsService.saveUser(new User("admin", passwordEncoder().encode("admin"), "ROLE_ADMIN"));
    userDetailsService.saveUser(new User("moderator", passwordEncoder().encode("moderator"), "ROLE_MODERATOR"));
    userDetailsService.saveUser(new User("emilia", passwordEncoder().encode("emilia"), "ROLE_USER"));
}
}
