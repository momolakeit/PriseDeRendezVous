package com.BarberShopManagement.PriseDeRendezVous.repositories;

import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StylesRepository extends CrudRepository<Styles,Long> {
}
