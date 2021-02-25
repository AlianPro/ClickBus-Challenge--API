package com.clickbus.clickbuschallenge.service;

import com.clickbus.clickbuschallenge.domain.Place;
import com.clickbus.clickbuschallenge.exception.BadRequestException;
import com.clickbus.clickbuschallenge.repository.PlaceRepository;
import com.clickbus.clickbuschallenge.util.PlaceCreator;
import com.clickbus.clickbuschallenge.util.PlacePostRequestBodyCreator;
import com.clickbus.clickbuschallenge.util.PlacePutRequestBodyCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class PlaceServiceTest {

    @InjectMocks
    private PlaceService placeService;
    @Mock
    private PlaceRepository placeRepositoryMock;

    @BeforeEach
    void setUP(){
        BDDMockito.when(placeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PlaceCreator.createValidPlace()));

        BDDMockito.when(placeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(PlaceCreator.createValidPlace()));

        BDDMockito.when(placeRepositoryMock.save(ArgumentMatchers.any(Place.class)))
                .thenReturn(PlaceCreator.createValidPlace());

        BDDMockito.doNothing().when(placeRepositoryMock).delete(ArgumentMatchers.any(Place.class));
    }

    @Test
    @DisplayName("findByName returns list of place when successful")
    void findByName_ReturnsListOfPlaces_WhenSuccessful(){
        String expectedName = PlaceCreator.createValidPlace().getName();
        List<Place> place = placeService.listPlaceFilteredByName("Gloria");
        Assertions.assertThat(place)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(place.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of place when place is not found")
    void findByName_ReturnsEmptyListOfPlace_WhenPlaceIsNotFound(){
        BDDMockito.when(placeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Place> place = placeService.listPlaceFilteredByName("Gloria");
        Assertions.assertThat(place)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("getSpecificPlaceByIdOrThrowBadRequestException returns place when successful")
    void getSpecificPlaceByIdOrThrowBadRequestException_ReturnsPlaces_WhenSuccessful(){
        Long expectedId = PlaceCreator.createValidPlace().getId();
        Place place = placeService.getSpecificPlaceByIdOrThrowBadRequestException(1L);
        Assertions.assertThat(place).isNotNull();
        Assertions.assertThat(place.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("getSpecificPlaceByIdOrThrowBadRequestException throws BadRequestException when place is not found")
    void getSpecificPlaceByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenPlaceIsNotFound(){
        BDDMockito.when(placeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(()->placeService.getSpecificPlaceByIdOrThrowBadRequestException(1L));
    }

    @Test
    @DisplayName("save returns place when successful")
    void save_ReturnsPlace_WhenSuccessful(){
        Place place = placeService.save(PlacePostRequestBodyCreator.createPlacePostRequestBody());
        Assertions.assertThat(place).isNotNull().isEqualTo(PlaceCreator.createValidPlace());
    }

    @Test
    @DisplayName("delete removes place when successful")
    void delete_RemovesPlace_WhenSuccessful(){
        Assertions.assertThatCode(()->placeService.delete(1L))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("replace updates place when successful")
    void replace_UpdatesPlace_WhenSuccessful(){
        Assertions.assertThatCode(()->placeService
                .replace(PlacePutRequestBodyCreator.createPlacePutRequestBody(),PlaceCreator.createValidPlace().getId()))
                .doesNotThrowAnyException();
    }
}