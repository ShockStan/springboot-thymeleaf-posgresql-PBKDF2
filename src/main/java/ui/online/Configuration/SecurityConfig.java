package ui.online.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.security.NoSuchAlgorithmException;

@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.build();
    }

    public PasswordEncoder passwordEncode() throws NoSuchAlgorithmException {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    public boolean verification(String password, String value) throws NoSuchAlgorithmException {
        return passwordEncode().matches(password, value);
    }

}
