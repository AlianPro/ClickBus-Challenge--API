package com.clickbus.clickbuschallenge.integration;

import com.clickbus.clickbuschallenge.domain.Place;
import com.clickbus.clickbuschallenge.repository.PlaceRepository;
import com.clickbus.clickbuschallenge.requests.PlacePostRequestBody;
import com.clickbus.clickbuschallenge.util.PlaceCreator;
import com.clickbus.clickbuschallenge.util.PlacePostRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlaceControllerIT {
    @Autowired
    @Qualifier("testRestTemplate")
    private TestRestTemplate testRestTemplate;
    @Autowired
    private PlaceRepository placeRepository;

    @TestConfiguration
    @Lazy
    static class Config{
        @Bean(name="testRestTemplate")
        public TestRestTemplate testRestTemplateCreator(@Value("${local.server.port}") int port){
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port);
            return new TestRestTemplate(restTemplateBuilder);
        }
    }

    @Test
    @DisplayName("findByName returns list of place when successful")
    void findByName_ReturnsListOfPlaces_WhenSuccessful(){
        Place savedPlace = placeRepository.save(PlaceCreator.createPlaceToBeSaved());
        String expectedName = savedPlace.getName();
        String url = String.format("/click/list?name=%s",expectedName);
        List<Place> places = testRestTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Place>>() {
                }).getBody();

        Assertions.assertThat(places)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(places.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of place when place is not found")
    void findByName_ReturnsEmptyListOfPlace_WhenPlaceIsNotFound(){
        List<Place> places = testRestTemplate.exchange("/click/list?name=angra",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Place>>() {
                }).getBody();

        Assertions.assertThat(places)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns place when successful")
    void save_ReturnsPlace_WhenSuccessful(){
        PlacePostRequestBody placePostRequestBody = PlacePostRequestBodyCreator.createPlacePostRequestBody();
        ResponseEntity<Place> placeResponseEntity = testRestTemplate.postForEntity("/click",
                placePostRequestBody,
                Place.class);
        Assertions.assertThat(placeResponseEntity).isNotNull();
        Assertions.assertThat(placeResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(placeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(placeResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("replace updates place when successful")
    void replace_UpdatesPlace_WhenSuccessful(){
        Place savedPlace = placeRepository.save(PlaceCreator.createPlaceToBeSaved());
        savedPlace.setName("angra");
        ResponseEntity<Void> placeResponseEntity = testRestTemplate.exchange("/click/replace/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(savedPlace),
                Void.class,savedPlace.getId());

        Assertions.assertThat(placeResponseEntity).isNotNull();
        Assertions.assertThat(placeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes place when successful")
    void delete_RemovesPlace_WhenSuccessful(){
        Place savedPlace = placeRepository.save(PlaceCreator.createPlaceToBeSaved());
        ResponseEntity<Void> placeResponseEntity = testRestTemplate.exchange("/click/remove/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                savedPlace.getId());

        Assertions.assertThat(placeResponseEntity).isNotNull();
        Assertions.assertThat(placeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("getSpecificPlaceByIdOrThrowBadRequestException returns place when successful")
    void getSpecificPlaceByIdOrThrowBadRequestException_ReturnsPlaces_WhenSuccessful(){
        Place savedPlace = placeRepository.save(PlaceCreator.createPlaceToBeSaved());
        Long expectedId = savedPlace.getId();
        Place place = testRestTemplate.getForObject("/click/get/{id}", Place.class, expectedId);
        Assertions.assertThat(place).isNotNull();
        Assertions.assertThat(place.getId()).isNotNull().isEqualTo(expectedId);
    }

}
