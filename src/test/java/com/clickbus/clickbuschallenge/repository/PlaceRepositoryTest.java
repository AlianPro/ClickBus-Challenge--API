package com.clickbus.clickbuschallenge.repository;

import com.clickbus.clickbuschallenge.domain.Place;
import com.clickbus.clickbuschallenge.util.PlaceCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Place Repository")
class PlaceRepositoryTest {
    @Autowired
    private PlaceRepository placeRepository;

    @Test
    @DisplayName("Save persists place when Successful")
    void save_PersistPlace_WhenSuccessful(){
        Place placeToBeSaved = PlaceCreator.createPlaceToBeSaved();
        Place placeSaved = this.placeRepository.save(placeToBeSaved);
        Assertions.assertThat(placeSaved).isNotNull();
        Assertions.assertThat(placeSaved.getId()).isNotNull();
        Assertions.assertThat(placeSaved.getName()).isEqualTo(placeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates place when Successful")
    void save_UpdatesPlace_WhenSuccessful(){
        Place placeToBeSaved = PlaceCreator.createPlaceToBeSaved();
        Place placeSaved = this.placeRepository.save(placeToBeSaved);
        placeSaved.setName("Lapa");
        Place placeUpdated = this.placeRepository.save(placeSaved);
        Assertions.assertThat(placeUpdated).isNotNull();
        Assertions.assertThat(placeUpdated.getId()).isNotNull();
        Assertions.assertThat(placeUpdated.getName()).isEqualTo(placeSaved.getName());
    }

    @Test
    @DisplayName("Delete removes place when Successful")
    void delete_RemovesPlace_WhenSuccessful(){
        Place placeToBeSaved = PlaceCreator.createPlaceToBeSaved();
        Place placeSaved = this.placeRepository.save(placeToBeSaved);
        this.placeRepository.delete(placeSaved);
        Optional<Place> placeOptional = this.placeRepository.findById(placeSaved.getId());
        Assertions.assertThat(placeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by name returns list of place when Successful")
    void findByName_ReturnsListOfPlace_WhenSuccessful(){
        Place placeToBeSaved = PlaceCreator.createPlaceToBeSaved();
        Place placeSaved = this.placeRepository.save(placeToBeSaved);
        String name = placeSaved.getName();
        List<Place> places = this.placeRepository.findByName(name);
        Assertions.assertThat(places).isNotEmpty().contains(placeSaved);
    }

    @Test
    @DisplayName("Find by name returns empty list when place is not found")
    void findByName_ReturnsEmptyList_WhenPlaceIsNotFound (){
        List<Place> places = this.placeRepository.findByName("AB");
        Assertions.assertThat(places).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty (){
        Place place = new Place();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()->this.placeRepository.save(place))
                .withMessageContaining("The Place name cannot be empty");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when slug is empty")
    void save_ThrowsConstraintViolationException_WhenSlugIsEmpty (){
        Place place = new Place();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()->this.placeRepository.save(place))
                .withMessageContaining("The Place slug cannot be empty");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when city is empty")
    void save_ThrowsConstraintViolationException_WhenCityIsEmpty (){
        Place place = new Place();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()->this.placeRepository.save(place))
                .withMessageContaining("The Place city cannot be empty");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when state is empty")
    void save_ThrowsConstraintViolationException_WhenStateIsEmpty (){
        Place place = new Place();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()->this.placeRepository.save(place))
                .withMessageContaining("The Place state cannot be empty");
    }
}