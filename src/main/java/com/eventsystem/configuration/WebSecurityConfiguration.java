package com.eventsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] staticResources = { "/css/**", "/images/**", "/fonts/**", "/scripts/**", };
        http.authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .antMatchers("chat/**", "/changePassword", "/history", "/events", "/generateQrCode").hasAnyRole("ADMIN", "USER", "MANAGER")
                .antMatchers("/h2/**", "/createUser/**").hasRole("ADMIN")
                .antMatchers("/createEvent/**", "/managerEvents/**", "/updateEvent/**", "/deleteEvent/**",
                        "/readQrCode/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("WEB-INF/jsps/**", "/**/*.png",
                        "/**/*.jpg").permitAll()
                .antMatchers("/register/**", "/resetPassword/**").permitAll()
                .antMatchers("/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/redirect", true).
                failureUrl("/error")
                .and().logout().logoutSuccessUrl("/login")
                .and()
                .logout().permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("VerySussyKey").tokenValiditySeconds(86400).rememberMeParameter("remember-me")
                .and()
                .csrf().disable().httpBasic()
                .and()
                .headers().frameOptions().disable()
        ;
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
