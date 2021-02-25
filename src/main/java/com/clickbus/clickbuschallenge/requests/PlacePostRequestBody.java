package com.clickbus.clickbuschallenge.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlacePostRequestBody {
    @NotEmpty(message = "The Place name cannot be empty")
    private String name;
    @NotEmpty(message = "The Place slug cannot be empty")
    private String slug;
    @NotEmpty(message = "The Place city cannot be empty")
    private String city;
    @NotEmpty(message = "The Place state cannot be empty")
    private String state;
}
