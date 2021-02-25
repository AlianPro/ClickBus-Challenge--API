package com.clickbus.clickbuschallenge.util;

import com.clickbus.clickbuschallenge.requests.PlacePutRequestBody;

public class PlacePutRequestBodyCreator {

    public static PlacePutRequestBody createPlacePutRequestBody(){
        return PlacePutRequestBody.builder()
                .name(PlaceCreator.createValidUpdatedPlace().getName())
                .slug(PlaceCreator.createValidUpdatedPlace().getSlug())
                .city(PlaceCreator.createValidUpdatedPlace().getCity())
                .state(PlaceCreator.createValidUpdatedPlace().getState())
                .build();
    }
}
