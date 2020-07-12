package com.BarberShopManagement.PriseDeRendezVous.event;

import com.BarberShopManagement.PriseDeRendezVous.entities.RendezVous;
import com.BarberShopManagement.PriseDeRendezVous.repositories.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Date;

public class ApplicationRunner implements CommandLineRunner {
    @Autowired
    RendezVousRepository rendezVousRepository;
    @Override
    public void run(String... args) throws Exception {
        RendezVous rendezVous=new RendezVous();
        rendezVous.setBarberEmail("Employee@gmail.com");
       // Date date = new Date()
        //rendezVous.setDateRendezVous();
    }
}
