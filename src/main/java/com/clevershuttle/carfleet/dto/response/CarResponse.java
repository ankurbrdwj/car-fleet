package com.clevershuttle.carfleet.dto.response;

import com.clevershuttle.carfleet.domain.Car;
import com.clevershuttle.carfleet.dto.request.CarRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Builder
@Getter
@Setter
public class CarResponse {
  private String brand;
  private String licensePlate;
  private String manufacturer;
  private String operationsCity;
  private String status;
  private String createdAt;
  private String lastUpdatedAt;

  public static CarResponse of(Car car){
    return CarResponse.builder()
      .brand(car.getBrand())
      .licensePlate(car.getLicensePlate())
      .manufacturer(car.getManufacturer())
      .operationsCity(car.getOperationsCity())
      .createdAt(car.getCreatedAt().toString())
      .lastUpdatedAt(car.getLastUpdatedAt().toString())
      .build();
  }
}
