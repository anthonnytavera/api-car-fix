package com.adtavera.apicarfix.controllers;

import com.adtavera.apicarfix.dtos.CarDto;
import com.adtavera.apicarfix.models.Car;
import com.adtavera.apicarfix.services.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = this.carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(
            @PathVariable("id") Long id
    ) {
        Car car = this.carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> create(@Valid @RequestBody CarDto carDto) {
        return ResponseEntity.ok(this.carService.create(carDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CarDto carDto
    ) {
        return ResponseEntity.ok(this.carService.update(id, carDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(this.carService.deleteById(id));
    }
}
