package com.BarberShopManagement.PriseDeRendezVous.models.mapping;

import com.BarberShopManagement.PriseDeRendezVous.models.dto.RendezVousDto;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RendezvVousToRendezVousdDTOMapper extends MapperInterface<RendezVousDto,RendezVous> {
    RendezvVousToRendezVousdDTOMapper instance = Mappers.getMapper(RendezvVousToRendezVousdDTOMapper.class);
    @Mapping(target = "styles", ignore = true)
    RendezVousDto convert(RendezVous rendezVous);
}
