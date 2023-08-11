package com.demos.paymybuddy.security;

import com.demos.paymybuddy.config.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    // c'est une methode qu'on a redefinis pour personnaliser les resources qui doivent etre public sans authentification
    // et aussi les resources qui doivent etre privee oubien securisé
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/static/**").permitAll()  // Authoriser l'acces au resources static comme les fichiers css et js
                .antMatchers("/registration/**").permitAll() // Authoriser au public l'action de la creation d'un compte
                .anyRequest().authenticated() // Le reste des resources seront securisé, chaque demande doit etre connecté pou avoir l'accès
                .and()
                .formLogin()
                .loginPage("/login") // Ici on a definis l'url de l'authenfication
                .defaultSuccessUrl("/home") // Après une connexion valide on redirect l'utilisateur a la page d'accueil
                .failureUrl("/login?error=true") // Sinon on demande les info de ceccnxion
                .successHandler(this.loginSuccessHandler()) // c'est un bean (un service de gestion de l'operation du login)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    // C'est un bean qui va etre le responsable de le chiffrement et de dechiffrement du password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // c'est un bean (un service de gestion de l'operation du login)
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

}
