package com.clickbus.clickbuschallenge.mapper;

import com.clickbus.clickbuschallenge.domain.Place;
import com.clickbus.clickbuschallenge.requests.PlacePostRequestBody;
import com.clickbus.clickbuschallenge.requests.PlacePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PlaceMapper {
    public static final PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);
    public abstract Place toPlace(PlacePostRequestBody placePostRequestBody);
    public abstract Place toPlace(PlacePutRequestBody placePutRequestBody);
}
