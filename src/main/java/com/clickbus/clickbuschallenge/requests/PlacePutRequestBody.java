package com.clickbus.clickbuschallenge.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlacePutRequestBody {
    private String name;
    private String slug;
    private String city;
    private String state;
}
