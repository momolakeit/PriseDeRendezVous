package com.BarberShopManagement.PriseDeRendezVous.models.dto;

import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;

import javax.persistence.*;
import java.util.List;

public class StylesDto {
    private long id;

    private String imageUrl;

    private String barberEmail;

    List<RendezVousDto> rendezVous;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBarberEmail() {
        return barberEmail;
    }

    public void setBarberEmail(String barberEmail) {
        this.barberEmail = barberEmail;
    }

    public List<RendezVousDto> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVousDto> rendezVous) {
        this.rendezVous = rendezVous;
    }
}
