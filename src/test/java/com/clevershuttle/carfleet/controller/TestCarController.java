package com.clevershuttle.carfleet.controller;

import com.clevershuttle.carfleet.domain.Car;
import com.clevershuttle.carfleet.dto.response.CarResponse;
import com.clevershuttle.carfleet.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@WebMvcTest(CarController.class)
public class TestCarController {
  @Autowired
  private MockMvc mockMvc;
  private ObjectMapper objectMapper = new ObjectMapper();
  @MockBean
  private CarService carService;
  @BeforeEach
  public void setUp(){

  }
  @Test
  void shouldGetEmptyWhenNoCarExist() throws Exception {
    MvcResult mvcResult = this.mockMvc
      .perform(get("/cars")
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
      .andExpect(status().is(200))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.size()", is(0)))
      .andDo(print())
      .andReturn();
  }

  @Test
  void shouldGetBooksWhenServiceReturnsBooks() throws Exception {

    CarResponse car1 = createBook(1L, "Flexa", "Ferrari", "Berlin", "L-CS8877E",
      "available");

    CarResponse car2 = createBook(2L, "Benz", "Mercedez", "Munic", "L-XX6655D",
      "in-maintenance");

    when(carService.getAll()).thenReturn(List.of(car1, car2));

    this.mockMvc
      .perform(get("/cars")
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
      .andExpect(status().is(200))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.size()", is(2)))
      .andExpect(jsonPath("$[0].brand", is("Flexa")))
      .andExpect(jsonPath("$[0].id").doesNotExist())
      .andExpect(jsonPath("$[0].manufacturer", is("Ferrari")))
      .andExpect(jsonPath("$[1].brand", is("Benz")))
      .andExpect(jsonPath("$[1].id").doesNotExist())
      .andExpect(jsonPath("$[1].manufacturer", is("Mercedez")));
  }

  private CarResponse createBook(Long id, String brand, String mf, String city, String license,String status) {
    return CarResponse.builder()
      .brand(brand)
      .licensePlate(license)
      .manufacturer(mf)
      .operationsCity(city)
      .createdAt(ZonedDateTime.now().toString())
      .lastUpdatedAt(ZonedDateTime.now().toString())
      .status(status)
      .build();
  }

}
