package com.BarberShopManagement.PriseDeRendezVous.controller;

import com.BarberShopManagement.PriseDeRendezVous.PriseDeRendezVousApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = PriseDeRendezVousApplication.class)
class PriseDeRendezVousControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createPrendreRendezVousDoitRetournerRendezVousAvecVal(){

    }

}