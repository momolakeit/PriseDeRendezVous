package com.BarberShopManagement.PriseDeRendezVous.event;

import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;
import com.BarberShopManagement.PriseDeRendezVous.repositories.RendezVousRepository;
import com.BarberShopManagement.PriseDeRendezVous.repositories.StylesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    StylesRepository stylesRepository;

    @Override
    public void run(String... args) throws Exception {
        RendezVous rendezVous=new RendezVous();
        rendezVous.setBarberEmail("Employee@gmail.com");
        rendezVousRepository.save(rendezVous);
        Styles styles = new Styles();
        stylesRepository.save(styles);

       // Date date = new Date()
        //rendezVous.setDateRendezVous();
    }
}
