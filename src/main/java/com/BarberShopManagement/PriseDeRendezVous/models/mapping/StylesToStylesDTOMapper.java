package com.BarberShopManagement.PriseDeRendezVous.models.mapping;

import com.BarberShopManagement.PriseDeRendezVous.models.dto.RendezVousDto;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.StylesDto;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StylesToStylesDTOMapper extends MapperInterface <StylesDto,Styles>{
    StylesToStylesDTOMapper instance = Mappers.getMapper(StylesToStylesDTOMapper.class);
    @Mapping(target = "rendezVous", ignore = true)
    StylesDto convert(Styles styles);
}
