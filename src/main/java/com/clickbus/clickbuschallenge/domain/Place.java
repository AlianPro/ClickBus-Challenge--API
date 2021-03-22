package com.clickbus.clickbuschallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "The Place name cannot be empty")
    private String name;
    @NotEmpty(message = "The Place slug cannot be empty")
    private String slug;
    @NotEmpty(message = "The Place city cannot be empty")
    private String city;
    @NotEmpty(message = "The Place state cannot be empty")
    private String state;
    private String created_at;
    private String updated_at;
}
