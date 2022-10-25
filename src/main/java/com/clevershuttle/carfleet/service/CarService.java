package com.clevershuttle.carfleet.service;

import com.clevershuttle.carfleet.domain.Car;
import com.clevershuttle.carfleet.domain.Status;
import com.clevershuttle.carfleet.dto.request.CarRequest;
import com.clevershuttle.carfleet.dto.response.CarResponse;
import com.clevershuttle.carfleet.exception.ElementNotFoundException;
import com.clevershuttle.carfleet.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

  private final CarRepository repository;

  CarService(CarRepository repository) {
    this.repository = repository;
  }

  public List<CarResponse> getAll() {
    return repository.findAll().stream()
      .map(CarResponse::of)
      .collect(Collectors.toList());
  }

  public CarResponse save(CarRequest newCar) {
    Car car = CarRequest.to(newCar);
    car.setCreatedAt(ZonedDateTime.now().toInstant());
    car.setLastUpdatedAt(ZonedDateTime.now().toInstant());
    car = repository.save(car);
    return CarResponse.of(car);
  }

  public CarResponse getOne(Long id) {
    Car car = repository.findById(id)
      .orElseThrow(() -> new ElementNotFoundException("Car with Id :" + id + " not found"));
    return CarResponse.of(car);
  }

  public CarResponse update(CarRequest modifiedCar, Long id) {
    Car car = repository.findById(id)
      .orElseThrow(() -> new ElementNotFoundException("Car with Id :" + id + " not found"));
    car.setBrand(modifiedCar.getBrand());
    car.setManufacturer(modifiedCar.getManufacturer());
    car.setLicensePlate(modifiedCar.getLicensePlate());
    car.setOperationsCity(modifiedCar.getOperationsCity());
    car.setStatus(Status.valueOf(modifiedCar.getStatus()));
    car.setLastUpdatedAt(ZonedDateTime.now().toInstant());
    car = repository.save(car);
    return CarResponse.of(car);
  }

  public void softDelete(Long id) {
    Car car = repository.findById(id)
      .orElseThrow(() -> new ElementNotFoundException("Car with Id :" + id + " not found"));
    car.setActive(false);
    car.setLastUpdatedAt(ZonedDateTime.now().toInstant());
     repository.save(car);
  }
}
