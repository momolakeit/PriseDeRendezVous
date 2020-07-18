package com.BarberShopManagement.PriseDeRendezVous.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@ConditionalOnProperty(name = "config.securite.active", havingValue = "false")
public class DisableResourceAccessConfiguration extends ResourceServerConfigurerAdapter implements RessouceAcessImpl {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }
}
