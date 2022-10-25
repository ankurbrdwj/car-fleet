package com.clevershuttle.carfleet.service;

import com.clevershuttle.carfleet.domain.Car;
import com.clevershuttle.carfleet.dto.request.CarRequest;
import com.clevershuttle.carfleet.dto.response.CarResponse;
import com.clevershuttle.carfleet.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class CarServiceTest {
  CarRequest request;
  @BeforeEach
  public void setup(){
    request = new CarRequest();
    request.setBrand("Flexa");
    request.setManufacturer("");
    request.setLicensePlate("L-CS8877E");
    request.setOperationsCity("Berlin");
    request.setStatus("available");
  }
  @Mock
  private CarRepository userRepository;

  @InjectMocks
  private CarService service;

  @Test
  void shouldGetCreatedTimeCreatingCar() {
    when(userRepository.save(any(Car.class))).thenAnswer(invocation -> {
      Car car = invocation.getArgument(0);
      car.setId(1L);
      return car;
    });

    ZonedDateTime defaultDateTime = ZonedDateTime.parse("2017-09-01T10:23:47.000Z");
    try (MockedStatic<ZonedDateTime> mockedZonedDateTime = Mockito.mockStatic(ZonedDateTime.class)) {
      mockedZonedDateTime.when(ZonedDateTime::now).thenReturn(defaultDateTime);
      System.out.println(LocalDateTime.now());
      CarResponse result = service.save(request);
      Assertions.assertEquals(defaultDateTime.toString(), result.getCreatedAt());
    }
  }
}
