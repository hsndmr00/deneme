package com.example.garageWithSpring.repository;

import com.example.garageWithSpring.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypeRepository  extends JpaRepository<VehicleType, String> {
}
