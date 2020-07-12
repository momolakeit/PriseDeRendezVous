package com.BarberShopManagement.PriseDeRendezVous.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Styles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;

    @Column
    private String imageUrl;

    @Column
    private String barberEmail;

    @Column
    @ManyToMany
    List<RendezVous> rendezVous;

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

    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public String getBarberEmail() {
        return barberEmail;
    }

    public void setBarberEmail(String barberEmail) {
        this.barberEmail = barberEmail;
    }
}
