package com.clevershuttle.carfleet.domain;

public enum Status {
  available("available"),
  inmaintenance("in-maintenance"),
  outofservice("out-of-service");

  private final String value;

  Status(String value) {
    this.value = value;
  }
}
