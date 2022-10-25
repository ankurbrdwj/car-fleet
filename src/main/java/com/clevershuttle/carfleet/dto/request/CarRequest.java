package com.clevershuttle.carfleet.dto.request;

import com.clevershuttle.carfleet.domain.Car;
import com.clevershuttle.carfleet.domain.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequest {
  private String brand;
  private String licensePlate;
  private String manufacturer;
  private String operationsCity;
  private String status;

  public static Car to(CarRequest request){
    Car car = new Car();
    car.setBrand(request.getBrand());
    car.setManufacturer(request.getManufacturer());
    car.setLicensePlate(request.getLicensePlate());
    car.setOperationsCity(request.getOperationsCity());
    car.setStatus(Status.valueOf(request.getStatus()));
    return car;
  }
}
