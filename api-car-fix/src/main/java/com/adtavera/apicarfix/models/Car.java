package com.adtavera.apicarfix.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year_car", nullable = false)
    private Integer year;

    @Column(name = "km", nullable = false)
    private Integer km;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "car", orphanRemoval = true)
    private List<Fix> fixes = new ArrayList<>();

    @OneToMany(mappedBy = "car", orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

}
