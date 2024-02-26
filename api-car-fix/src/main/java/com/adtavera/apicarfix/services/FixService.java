package com.adtavera.apicarfix.services;

import com.adtavera.apicarfix.dtos.CarDto;
import com.adtavera.apicarfix.dtos.FixDto;
import com.adtavera.apicarfix.exceptions.NotFoundException;
import com.adtavera.apicarfix.models.Car;
import com.adtavera.apicarfix.models.Fix;
import com.adtavera.apicarfix.models.User;
import com.adtavera.apicarfix.repositories.CarRepository;
import com.adtavera.apicarfix.repositories.FixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FixService {

    @Autowired
    private FixRepository fixRepository;

    @Autowired
    private CarRepository carRepository;

    @Transactional(readOnly = true)
    public Fix findById(Long id) {
        return fixRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                "Fix with ID: " + id + " not found."));
    }

    @Transactional(readOnly = true)
    public List<Fix> findAll() {
        return (List<Fix>) fixRepository.findAll();
    }

    @Transactional
    public Fix create(FixDto fixDto) {
        Car car = carRepository
                .findById(fixDto.getCarId())
                .orElseThrow(
                        () -> new NotFoundException(
                                "Car with ID: " + fixDto.getCarId() + " not found."));

        Fix fix = new Fix();
        fix.setDescription(fixDto.getDescription());
        fix.setDate(fixDto.getDate());
        fix.setKm(fixDto.getKm());
        fix.setPrice(fixDto.getPrice());
        fix.setCar(car);

        return fixRepository.save(fix);
    }
}
