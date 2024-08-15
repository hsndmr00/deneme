package com.example.garageWithSpring.repository;

import com.example.garageWithSpring.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
