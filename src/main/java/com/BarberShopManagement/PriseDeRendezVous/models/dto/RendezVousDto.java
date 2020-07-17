package com.BarberShopManagement.PriseDeRendezVous.models.dto;

import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class RendezVousDto {
    private long id;


    private Date dateRendezVous;


    private String barberEmail;


    private String clientEmail;
    List<StylesDto> styles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(Date dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public String getBarberEmail() {
        return barberEmail;
    }

    public void setBarberEmail(String barberEmail) {
        this.barberEmail = barberEmail;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public List<StylesDto> getStyles() {
        return styles;
    }

    public void setStyles(List<StylesDto> styles) {
        this.styles = styles;
    }
}
