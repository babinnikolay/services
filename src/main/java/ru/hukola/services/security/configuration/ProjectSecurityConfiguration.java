package ru.hukola.services.security.configuration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import ru.hukola.services.security.filter.*;
import ru.hukola.services.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Babin Nikolay
 */
@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfiguration {

    private final UserService userService;

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
        Set<String> csrfAllowedMethods = Set.of("GET", "HEAD", "TRACE", "OPTIONS");
        CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();
        csrfTokenRepository.setCookieCustomizer(builder -> {
            builder.value(UUID.randomUUID().toString()).sameSite("string").maxAge(8600).httpOnly(false).build();
        });
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request ->
                        new CorsServices(frontedAddress, jwtHeader, tokenExpirationTime).getCorsConfiguration(request)))
                .csrf(csrf -> csrf.requireCsrfProtectionMatcher(
                                request -> !csrfAllowedMethods.contains(request.getMethod()))
                        .ignoringRequestMatchers("/login", "/logout", "/register")
                        .csrfTokenRepository(csrfTokenRepository)
                        .csrfTokenRequestHandler(requestHandler)
                )
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(userService, jwtKey, applicationName,
                                tokenName, jwtHeader, tokenExpirationTime),
                        BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(jwtKey, jwtHeader), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/orders/**", "/clients/**", "/change/**", "/feedback", "/reports/**").authenticated()
                        .requestMatchers("/login", "/logout", "/register").permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
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
