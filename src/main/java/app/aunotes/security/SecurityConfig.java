package app.aunotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (enable in production with proper tokens)
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // allow H2 console
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/",
//                                "/h2-console/**",
//                                "/api/users/signup",
//                                "/api/users/login",
//                                "/api/notes",
//                                "/api/notes/upload",
//                                "/api/notes/**"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults()); // Basic auth for now
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for easier testing
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // For H2 console
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 🔓 Make everything public
                )
                .httpBasic(httpBasic -> {}); // Optional if using Basic Auth later

        return http.build();
    }

}
