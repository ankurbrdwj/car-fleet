package com.clevershuttle.carfleet.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity (name ="Car")
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class Car extends AbstractEntity implements Serializable {

  @Column(name ="brand")
  private String brand;

  @Column(name ="licenseplate")
  private String licensePlate;

  @Column(name ="manufacturer")
  private String manufacturer;

  @Column(name ="operationscity")
  private String operationsCity;

  @Column(name ="status")
  private Enum status;



}
