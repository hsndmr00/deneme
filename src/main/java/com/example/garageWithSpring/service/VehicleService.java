package com.example.garageWithSpring.service;

import com.example.garageWithSpring.model.Vehicle;
import com.example.garageWithSpring.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteById(String id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }
}
