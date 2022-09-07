package com.config;

import com.model.SecurityUser;
import com.model.User;
import com.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(
                username -> {
                    User user = userRepository.findByUsername(username).
                            orElseThrow(() -> new UsernameNotFoundException(
                                    username + " not founded! "));
                    return new SecurityUser(user);
                });

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/companies/companies").hasAnyAuthority("ADMIN", "INSTRUCTOR", "STUDENT", "USER")
                        .antMatchers("/companies/addCompany").hasAuthority("ADMIN")
//                        .antMatchers(HttpMethod.POST, "/companies/saveCompany").hasAuthority("ADMIN")
                        .antMatchers("/companies/edit/**").hasAnyAuthority("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/companies/deleteCompany/**/").hasAnyAuthority("ADMIN", "INSTRUCTOR")
//                        .antMatchers(HttpMethod.PATCH, "/companies/updateCompany/**").hasAnyAuthority("ADMIN", "INSTRUCTOR")
                        .antMatchers("/courses/courses").hasAnyAuthority("ADMIN", "INSTRUCTOR", "STUDENT")
                        .antMatchers("/lessons/lessons").hasAnyAuthority("ADMIN", "INSTRUCTOR", "STUDENT")
                        .antMatchers("/tasks/tasks").hasAnyAuthority("INSTRUCTOR", "STUDENT")
                        .antMatchers("/videos/videos").hasAnyAuthority("INSTRUCTOR", "STUDENT")
                        .anyRequest().authenticated())
                .formLogin().
                defaultSuccessUrl("/companies")
                .permitAll();
        return http.build();
    }
}
