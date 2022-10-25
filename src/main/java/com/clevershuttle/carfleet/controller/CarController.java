package com.clevershuttle.carfleet.controller;

import com.clevershuttle.carfleet.dto.request.CarRequest;
import com.clevershuttle.carfleet.dto.response.CarResponse;
import com.clevershuttle.carfleet.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class CarController {
  private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

  private final CarService service;

  CarController(CarService service) {
    this.service = service;
  }

  @GetMapping("/cars")
  Collection<CarResponse> getAll() {
    return service.getAll();
  }

  @PostMapping("/cars")
  CarResponse createCar(@RequestBody CarRequest newCar) {
    return service.save(newCar);
  }

  @GetMapping("/cars/{id}")
  CarResponse getOne(@PathVariable Long id) {
    return service.getOne(id);
  }

  @PutMapping("/cars/{id}")
  CarResponse replaceCar(@RequestBody CarRequest modifiedCar, @PathVariable Long id) {
    return service.update(modifiedCar,id);
  }

  @DeleteMapping("/cars/{id}")
  void deleteCar(@PathVariable Long id) {
    service.softDelete(id);
  }

}
