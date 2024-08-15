package com.example.garageWithSpring.service;

import com.example.garageWithSpring.model.Slot;
import com.example.garageWithSpring.model.Vehicle;
import com.example.garageWithSpring.model.VehicleType;
import com.example.garageWithSpring.type.GetStatusOutputModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class StatusService {
    public List<GetStatusOutputModel> getVehiclesStatus(List<Vehicle> vehicles, List<VehicleType> vehicleTypes) {
        List<GetStatusOutputModel> statusList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            List<String> slots = new ArrayList<>();
            VehicleType foundVehicleType = null;
            for (VehicleType vehicleType : vehicleTypes) {
                if(Objects.equals(vehicleType.getName(), vehicle.getType())) {
                    foundVehicleType = vehicleType;
                }
            }
            int count = 0;
            for (Slot slot : vehicle.getSlots().stream()
                    .sorted(Comparator.comparing(slot -> Integer.parseInt(slot.getSlotNo())))
                    .toList()) {
                if (count < foundVehicleType.getSize()) {
                    slots.add(slot.getSlotNo());
                }
                count = count + 1;

            }
            GetStatusOutputModel status = new GetStatusOutputModel();
            status.setSlots(slots);
            status.setPlateCode(vehicle.getPlateCode());
            status.setColor(vehicle.getColor());
            status.setTicketNo(vehicle.getTicketNo());
            status.setVehicleType(vehicle.getType());
            statusList.add(status);
        }

        return statusList;
    }
}
