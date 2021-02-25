package com.clickbus.clickbuschallenge.service;

import com.clickbus.clickbuschallenge.domain.Place;
import com.clickbus.clickbuschallenge.exception.BadRequestException;
import com.clickbus.clickbuschallenge.mapper.PlaceMapper;
import com.clickbus.clickbuschallenge.repository.PlaceRepository;
import com.clickbus.clickbuschallenge.requests.PlacePostRequestBody;
import com.clickbus.clickbuschallenge.requests.PlacePutRequestBody;
import com.clickbus.clickbuschallenge.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<Place> listPlaceFilteredByName(String name){
        return placeRepository.findByName(name);
    }

    public Place getSpecificPlaceByIdOrThrowBadRequestException(long id){
         return placeRepository.findById(id)
                 .orElseThrow(()-> new BadRequestException("Place ID not found"));
    }

    public Place save(PlacePostRequestBody placePostRequestBody){
        Place place = PlaceMapper.INSTANCE.toPlace(placePostRequestBody);
        place.setCreatedAt(DateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return placeRepository.save(place);
    }

    public void delete(long id){
        placeRepository.delete(getSpecificPlaceByIdOrThrowBadRequestException(id));
    }

    public void replace(PlacePutRequestBody placePutRequestBody, long id){
        Place savedPlace = getSpecificPlaceByIdOrThrowBadRequestException(id);
        Place place = PlaceMapper.INSTANCE.toPlace(placePutRequestBody);
        place.setId(savedPlace.getId());
        place.setCreatedAt(savedPlace.getCreatedAt());
        place.setUpdatedAt(DateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        placeRepository.save(place);
    }
}
