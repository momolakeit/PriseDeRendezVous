package com.BarberShopManagement.PriseDeRendezVous.models.mapping;

import org.mapstruct.factory.Mappers;

public interface MapperInterface<T, Q> {
    MapperInterface instance = Mappers.getMapper(MapperInterface.class);
    T convert(Q objToConvert);

}
