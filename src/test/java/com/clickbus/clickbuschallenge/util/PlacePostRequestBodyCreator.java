package com.clickbus.clickbuschallenge.util;

import com.clickbus.clickbuschallenge.requests.PlacePostRequestBody;

public class PlacePostRequestBodyCreator {

    public static PlacePostRequestBody createPlacePostRequestBody(){
        return PlacePostRequestBody.builder()
                .name(PlaceCreator.createPlaceToBeSaved().getName())
                .slug(PlaceCreator.createPlaceToBeSaved().getSlug())
                .city(PlaceCreator.createPlaceToBeSaved().getCity())
                .state(PlaceCreator.createPlaceToBeSaved().getState())
                .build();
    }
}
