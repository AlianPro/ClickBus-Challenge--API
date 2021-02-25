package com.clickbus.clickbuschallenge.repository;

import com.clickbus.clickbuschallenge.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Long> {

    List<Place> findByName(String name);
}
