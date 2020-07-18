package com.BarberShopManagement.PriseDeRendezVous.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@ConditionalOnProperty (name = "config.securite.active", havingValue = "false")
public class DisableSecurityConfigurer extends WebSecurityConfigurerAdapter implements  SecuriteConfigImpl {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll().
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

        http.headers().frameOptions().disable();
    }
}