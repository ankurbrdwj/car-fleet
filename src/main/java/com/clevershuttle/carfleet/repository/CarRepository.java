package com.clevershuttle.carfleet.repository;

import com.clevershuttle.carfleet.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

}