package com.BarberShopManagement.PriseDeRendezVous.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;

    @Column
    private Date dateRendezVous;

    @Column
    private String barberEmail;

    @Column
    private String clientEmail;

    @Column
    @ManyToMany
    List<Styles> styles;

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



    public List<Styles> getStyles() {
        return styles;
    }

    public void setStyles(List<Styles> styles) {
        this.styles = styles;
    }

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


}
