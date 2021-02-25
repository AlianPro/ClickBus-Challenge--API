package com.clickbus.clickbuschallenge.controller;

import com.clickbus.clickbuschallenge.domain.Place;
import com.clickbus.clickbuschallenge.requests.PlacePostRequestBody;
import com.clickbus.clickbuschallenge.requests.PlacePutRequestBody;
import com.clickbus.clickbuschallenge.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("click")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @Operation(summary = "List places and filter them by name")
    @GetMapping(path ="/list")
    public ResponseEntity<List<Place>> listPlaceFilteredByName(@RequestParam String name){
        return ResponseEntity.ok(placeService.listPlaceFilteredByName(name));
    }

    @Operation(summary = "Create a place")
    @PostMapping
    public ResponseEntity<Place> save(@RequestBody @Valid PlacePostRequestBody placePostRequestBody){
        return new ResponseEntity<>(placeService.save(placePostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Edit a place")
    @PutMapping(path = "/replace/{id}")
    public ResponseEntity<Void> replace(@RequestBody PlacePutRequestBody placePutRequestBody ,@PathVariable long id){
        placeService.replace(placePutRequestBody, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Successful Operation"),
            @ApiResponse(responseCode = "400",description = "When Place Does Not Exist in The Database")
    })
    @Operation(summary = "Delete a place")
    @DeleteMapping(path = "/remove/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        placeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get a specific place")
    @GetMapping(path ="/get/{id}")
    public ResponseEntity<Place> getSpecificPlace(@PathVariable long id){
        return ResponseEntity.ok(placeService.getSpecificPlaceByIdOrThrowBadRequestException(id));
    }
}
