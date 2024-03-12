package cgg.quizapp.brainybits.config;

import cgg.quizapp.brainybits.helpers.CustomUserDetailsService;
import cgg.quizapp.brainybits.security.JwtAuthEntryPoint;
import cgg.quizapp.brainybits.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private JwtAuthEntryPoint entryPoint;

  @Autowired
  private JwtAuthFilter authFilter;

  @Bean
  SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/users/**")
          .hasAnyRole("USER", "ADMIN")
          .requestMatchers("/admin/**")
          .hasRole("ADMIN")
          .requestMatchers("/auth/**")
          .permitAll()
          .requestMatchers(HttpMethod.GET)
          .permitAll()
      )
      .exceptionHandling(e -> e.authenticationEntryPoint(entryPoint))
      .sessionManagement(s ->
        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      );
    http.addFilterBefore(
      authFilter,
      UsernamePasswordAuthenticationFilter.class
    );
    return http.build();
  }

  @Bean
  DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(customUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManagerBean(
    AuthenticationConfiguration config
  ) throws Exception {
    return config.getAuthenticationManager();
  }
}
