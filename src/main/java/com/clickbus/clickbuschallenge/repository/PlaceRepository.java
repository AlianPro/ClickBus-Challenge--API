package com.clickbus.clickbuschallenge.repository;

import com.clickbus.clickbuschallenge.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Long> {

    @Query("SELECT place FROM Place place WHERE place.name LIKE %:name%")
    List<Place> findByName(String name);
}
