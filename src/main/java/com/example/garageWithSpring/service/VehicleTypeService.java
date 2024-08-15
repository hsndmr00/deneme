package com.example.garageWithSpring.service;

import com.example.garageWithSpring.model.VehicleType;
import com.example.garageWithSpring.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    public List<VehicleType> findAll(){
        return  vehicleTypeRepository.findAll();
    }

    public VehicleType save(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    public void deleteById(String id) {
        vehicleTypeRepository.deleteById(id);
    }

    public VehicleType findById(String id) {
        return vehicleTypeRepository.findById(id).orElse(null);
    }
}
