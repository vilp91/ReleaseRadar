package release.radar.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(antMatcher("/css/**")).permitAll()
            .requestMatchers(antMatcher("/signup")).permitAll()
            .requestMatchers(antMatcher("/saveuser")).permitAll()
            .requestMatchers(antMatcher("/h2-console/**")).permitAll() // Allow H2 console access
            .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(antMatcher("/h2-console/**")) // Ignore CSRF for H2 console
        )
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions.disable()) // Allow frames for H2 console
        )
        .formLogin(formlogin -> formlogin
            .loginPage("/login")
            .defaultSuccessUrl("/studentlist", true)
            .permitAll()
        )
        .logout(logout -> logout
            .permitAll()
        );
        
        return http.build();   
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}