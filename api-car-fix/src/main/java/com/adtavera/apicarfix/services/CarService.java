package com.adtavera.apicarfix.services;

import com.adtavera.apicarfix.dtos.CarDto;
import com.adtavera.apicarfix.exceptions.NotFoundException;
import com.adtavera.apicarfix.models.Car;
import com.adtavera.apicarfix.models.User;
import com.adtavera.apicarfix.repositories.CarRepository;
import com.adtavera.apicarfix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Car findById(Long id) {
        Car car = carRepository.findById(id)
                .orElse(null);

        if (car == null) {
            throw new NotFoundException("Car with ID: " + id + " not found.");
        }

        return car;
    }

    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return (List<Car>) carRepository.findAll();
    }

    @Transactional
    public Car create(CarDto carDto) {
        User user = userRepository
                .findById(carDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User with ID: " + carDto.getUserId() + " not found."));

        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setKm(carDto.getKm());
        car.setUser(user);

        return carRepository.save(car);
    }

    @Transactional
    public Car update(Long id, CarDto carDto) {

        Car currentCar = carRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Car with ID: " + id + " not found."));

        User user = userRepository
                .findById(carDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User with ID: " + carDto.getUserId() + " not found."));

        currentCar.setBrand(carDto.getBrand());
        currentCar.setModel(carDto.getModel());
        currentCar.setYear(carDto.getYear());
        currentCar.setKm(carDto.getKm());
        currentCar.setUser(user);

        return carRepository.save(currentCar);

    }

    @Transactional
    public boolean deleteById(Long id) {
        carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with ID: " + id + " not found."));

        return true;
    }
}
