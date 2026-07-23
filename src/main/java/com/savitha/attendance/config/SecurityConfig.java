package com.savitha.attendance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.savitha.attendance.security.CustomUserDetailsService;
import com.savitha.attendance.security.JwtAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;		
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customUserDetailsService);

		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration)
					throws Exception {

		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {

		http

		.cors(cors -> cors.configurationSource(
				corsConfigurationSource()))

		.csrf(csrf -> csrf.disable())

		.sessionManagement(session ->
		session.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS))

		.exceptionHandling(exception -> exception
				.authenticationEntryPoint((request, response, authException) -> {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setContentType("application/json");
					response.getWriter().write(
							"{\"message\":\"Authentication is required to access this resource\"}"
							);
				})
				.accessDeniedHandler((request, response, accessDeniedException) -> {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					response.setContentType("application/json");
					response.getWriter().write(
							"{\"message\":\"You do not have permission to access this resource\"}"
							);
				})
				)

		.authorizeHttpRequests(auth -> auth

				.requestMatchers("/auth/**").permitAll()

				.requestMatchers("/employees/**")
				.hasRole("ADMIN")

				.requestMatchers("/leaves/**")
				.hasAnyRole("ADMIN", "EMPLOYEE")

				.requestMatchers("/attendance/**")
				.hasAnyRole("ADMIN", "EMPLOYEE")

				.anyRequest().authenticated())

		.authenticationProvider(authenticationProvider())

		.addFilterBefore(
				jwtAuthenticationFilter,
				UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(
				List.of("http://localhost:5173"));

		configuration.setAllowedMethods(
				List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		configuration.setAllowedHeaders(
				List.of("Authorization", "Content-Type"));

		configuration.setExposedHeaders(
				List.of("Authorization"));

		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source =
				new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}