package com.BarberShopManagement.PriseDeRendezVous.repositories;

import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RendezVousRepository extends CrudRepository<RendezVous,Long> {
    public List<RendezVous> findByBarberEmail(String email);
    public List<RendezVous> findByClientEmail(String email);

}
