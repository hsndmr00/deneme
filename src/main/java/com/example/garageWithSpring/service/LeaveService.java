package com.example.garageWithSpring.service;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.garageWithSpring.model.Slot;
import com.example.garageWithSpring.model.Vehicle;
import com.example.garageWithSpring.type.ErrorMessage;
import com.example.garageWithSpring.type.ResponseBodyMessage;

@Service
public class LeaveService {
    private final VehicleService vehicleService;
    private final SlotService slotService;

    public LeaveService(VehicleService vehicleService, SlotService slotService) {
        this.vehicleService = vehicleService;
        this.slotService = slotService;
    }

    public ResponseBodyMessage leaveVehicle(String ticketNo) throws ErrorMessage {
        System.out.println(ticketNo + "input TicketNo");
        List<Vehicle> vehicles = vehicleService.findAll();
        Vehicle foundVehicle = null;
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getTicketNo() + " Vehicle Ticket No");
            if (vehicle.getTicketNo().equals(ticketNo)) {
                foundVehicle = vehicle;
            }
        }

        if (foundVehicle == null) {
            throw new ErrorMessage("Vehicle not found", HttpStatus.BAD_REQUEST.value());
        }

        List<Slot> slots = slotService.findAll();

        for (Slot slot : slots) {
            if (Objects.equals(slot.getTicketNo(), foundVehicle.getTicketNo())) {
                slotService.deleteById(slot.getSlotNo());
            }
        }

        vehicleService.deleteById(foundVehicle.getPlateCode());

        return new ResponseBodyMessage("Vehicle left successfully");
    }
}
