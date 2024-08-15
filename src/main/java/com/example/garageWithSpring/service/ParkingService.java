package com.example.garageWithSpring.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.garageWithSpring.model.Slot;
import com.example.garageWithSpring.model.Vehicle;
import com.example.garageWithSpring.model.VehicleType;
import com.example.garageWithSpring.type.ErrorMessage;
import com.example.garageWithSpring.type.ResponseBodyMessage;
import com.example.garageWithSpring.type.UpdateVehicleInput;
import com.example.garageWithSpring.utlis.Utils;

@Service
public class ParkingService {
    private final VehicleTypeService vehicleTypeService;
    private final VehicleService vehicleService;
    private final SlotService slotService;

    public ParkingService(VehicleTypeService vehicleTypeService, VehicleService vehicleService,
            SlotService slotService) {
        this.vehicleTypeService = vehicleTypeService;
        this.vehicleService = vehicleService;
        this.slotService = slotService;
    }

    public ResponseBodyMessage parkVehicle(Vehicle vehicle) throws ErrorMessage {
        Vehicle hasPlateCode = vehicleService.findById(vehicle.getPlateCode());
        if (hasPlateCode != null) {
            throw new ErrorMessage("The car is already parked. Call updateVehicle method to update",
                    HttpStatus.BAD_REQUEST.value());
        }

        VehicleType vehicleType = vehicleTypeService.findById(vehicle.getType());
        if (vehicleType == null) {
            throw new ErrorMessage("Vehicle type not found", HttpStatus.BAD_REQUEST.value());
        }

        String ticketNo = Utils.getTicketNo(vehicleService.findAll());
        vehicle.setTicketNo(ticketNo);
        Vehicle newVehicle = Utils.vehicleCondition(vehicle);

        List<Slot> slots = slotService.findAll();
        List<Slot> newSlots = Utils.generateSlots(slots, newVehicle, vehicleType);

        vehicleService.save(newVehicle);
        slotService.saveAll(newSlots);

        return new ResponseBodyMessage("Vehicle parked successfully");
    }

    public ResponseBodyMessage updateVehicle(String plateCode, UpdateVehicleInput input) throws ErrorMessage {
        Vehicle vehicle = vehicleService.findById(plateCode);
        if (vehicle == null) {
            throw new ErrorMessage("Vehicle not found", HttpStatus.BAD_REQUEST.value());
        }
        vehicle.setColor(input.getColor());
        vehicleService.save(vehicle);
        return new ResponseBodyMessage("Vehicle updated successfully");
    }
}
