package com.clickbus.clickbuschallenge.util;

import com.clickbus.clickbuschallenge.domain.Place;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class PlaceCreator {

    public static Place createPlaceToBeSaved(){
        return Place.builder()
                .name("Gloria")
                .slug("GL")
                .city("RJ")
                .state("RJ")
                .build();
    }

    public static Place createValidPlace(){
        return Place.builder()
                .id(1L)
                .name("Gloria")
                .slug("GL")
                .city("RJ")
                .state("RJ")
                .createdAt("2021-02-24 01:49:30")
                .updatedAt("2021-02-24 02:49:30")
                .build();
    }

    public static Place createValidUpdatedPlace(){
        return Place.builder()
                .name("Gloria")
                .slug("GL")
                .city("RJ")
                .state("RJ")
                .build();
    }
}
