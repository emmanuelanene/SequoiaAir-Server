package com.emmanuelanene.sequoia_air.entities;

import com.emmanuelanene.sequoia_air.enums.City;
import com.emmanuelanene.sequoia_air.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "airports")
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private City city;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(unique = true, nullable = false, length = 3)
    private String iataCode;
}
