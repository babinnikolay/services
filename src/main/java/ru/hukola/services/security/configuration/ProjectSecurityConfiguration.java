package ru.hukola.services.security.configuration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import ru.hukola.services.security.filter.CsrfCookieFilter;
import ru.hukola.services.security.filter.JWTTokenGeneratorFilter;
import ru.hukola.services.security.filter.JWTTokenValidatorFilter;
import ru.hukola.services.security.filter.RequestValidationBeforeFilter;

import java.util.Collections;
import java.util.List;

/**
 * @author Babin Nikolay
 */
@Configuration
public class ProjectSecurityConfiguration {

    @Value("${servicer.security.csrf-prefix}")
    private String prefix;

    @Value("${servicer.frontend-address}")
    private String frontedAddress;

    @Value("${servicer.security.JWT_HEADER}")
    private String jwtHeader;

    @Value("${servicer.security.token-expiration-time}")
    private long tokenExpirationTime;

    @Value("${servicer.security.JWT_KEY}")
    private String jwtKey;

    @Value("${servicer.application.name}")
    private String applicationName;

    @Value("${servicer.application.token-name}")
    private String tokenName;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request ->
                        new CorsServices(frontedAddress, jwtHeader, tokenExpirationTime).getCorsConfiguration(request)))
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/login")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(jwtKey, applicationName, tokenName, jwtHeader, tokenExpirationTime),
                        BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(jwtKey, jwtHeader), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/orders", "/clients").authenticated()
                        .requestMatchers("/login").permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}

@AllArgsConstructor
class CorsServices implements CorsConfigurationSource {
    private final String frontedAddress;
    private final String jwtHeader;
    private final long tokenExpirationTime;

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList(frontedAddress));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setExposedHeaders(List.of(jwtHeader));
        config.setMaxAge(tokenExpirationTime);
        return config;
    }
}
