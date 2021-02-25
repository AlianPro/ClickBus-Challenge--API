package com.clickbus.clickbuschallenge.controller;

import com.clickbus.clickbuschallenge.domain.Place;
import com.clickbus.clickbuschallenge.exception.BadRequestException;
import com.clickbus.clickbuschallenge.requests.PlacePostRequestBody;
import com.clickbus.clickbuschallenge.requests.PlacePutRequestBody;
import com.clickbus.clickbuschallenge.service.PlaceService;
import com.clickbus.clickbuschallenge.util.PlaceCreator;
import com.clickbus.clickbuschallenge.util.PlacePostRequestBodyCreator;
import com.clickbus.clickbuschallenge.util.PlacePutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class PlaceControllerTest {

    @InjectMocks
    private PlaceController placeController;
    @Mock
    private PlaceService placeServiceMock;

    @BeforeEach
    void setUP(){
        BDDMockito.when(placeServiceMock.listPlaceFilteredByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PlaceCreator.createValidPlace()));

        BDDMockito.when(placeServiceMock.save(ArgumentMatchers.any(PlacePostRequestBody.class)))
                .thenReturn(PlaceCreator.createValidPlace());

        BDDMockito.doNothing().when(placeServiceMock)
                .replace(ArgumentMatchers.any(PlacePutRequestBody.class)
                        ,ArgumentMatchers.eq(PlaceCreator.createValidPlace().getId()));

        BDDMockito.doNothing().when(placeServiceMock).delete(ArgumentMatchers.anyLong());

        BDDMockito.when(placeServiceMock.getSpecificPlaceByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(PlaceCreator.createValidPlace());
    }

    @Test
    @DisplayName("findByName returns list of place when successful")
    void findByName_ReturnsListOfPlaces_WhenSuccessful(){
        String expectedName = PlaceCreator.createValidPlace().getName();
        List<Place> place = placeController.listPlaceFilteredByName("Gloria").getBody();
        Assertions.assertThat(place)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(place.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of place when place is not found")
    void findByName_ReturnsEmptyListOfPlace_WhenPlaceIsNotFound(){
        BDDMockito.when(placeServiceMock.listPlaceFilteredByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Place> place = placeController.listPlaceFilteredByName("Gloria").getBody();
        Assertions.assertThat(place)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns place when successful")
    void save_ReturnsPlace_WhenSuccessful(){
        Place place = placeController.save(PlacePostRequestBodyCreator.createPlacePostRequestBody()).getBody();
        Assertions.assertThat(place).isNotNull().isEqualTo(PlaceCreator.createValidPlace());
    }

    @Test
    @DisplayName("replace updates place when successful")
    void replace_UpdatesPlace_WhenSuccessful(){
        Assertions.assertThatCode(()->placeController
                .replace(PlacePutRequestBodyCreator.createPlacePutRequestBody(),PlaceCreator.createValidPlace().getId()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = placeController
                .replace(PlacePutRequestBodyCreator.createPlacePutRequestBody(), PlaceCreator.createValidPlace().getId());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes place when successful")
    void delete_RemovesPlace_WhenSuccessful(){
        Assertions.assertThatCode(()->placeController.delete(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = placeController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("getSpecificPlaceByIdOrThrowBadRequestException returns place when successful")
    void getSpecificPlaceByIdOrThrowBadRequestException_ReturnsPlaces_WhenSuccessful(){
        Long expectedId = PlaceCreator.createValidPlace().getId();
        Place place = placeController.getSpecificPlace(1L).getBody();
        Assertions.assertThat(place).isNotNull();
        Assertions.assertThat(place.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("getSpecificPlaceByIdOrThrowBadRequestException throws BadRequestException when place is not found")
    void getSpecificPlaceByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenPlaceIsNotFound(){
        BDDMockito.when(placeServiceMock.getSpecificPlaceByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenThrow(new BadRequestException("Place ID not found"));

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(()->placeController.getSpecificPlace(1L));
    }


}