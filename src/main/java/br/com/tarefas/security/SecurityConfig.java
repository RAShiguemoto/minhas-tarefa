package br.com.tarefas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		    .csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/api/auth/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/tarefas/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/tarefas/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/tarefas/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/tarefas/**").hasAnyRole("ADMIN", "USER")
				.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
			)
			.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.httpBasic(Customizer.withDefaults());

		return http.build();
	}

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user =
             User.builder()
                .username("usuario")
                .password(passwordEncoder().encode("senha"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
