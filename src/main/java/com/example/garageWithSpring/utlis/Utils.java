package com.example.garageWithSpring.utlis;

import com.example.garageWithSpring.model.Slot;
import com.example.garageWithSpring.model.Vehicle;
import com.example.garageWithSpring.model.VehicleType;
import com.example.garageWithSpring.type.ErrorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    static String plateRegex = "^((0[1-9])|([1-7][0-9])|80|81)-[A-VY-Z]{1,3}-\\d{1,4}$";

    public Utils() {}

    public static Vehicle vehicleCondition(Vehicle vehicle ) throws ErrorMessage {
        Pattern pattern = Pattern.compile(plateRegex);
        Matcher matcher = pattern.matcher(vehicle.getPlateCode());
        if (!matcher.matches()) throw new ErrorMessage("Invalid plate code");
        return vehicle;
    }

    public static String getTicketNo(List<Vehicle> vehicleList) throws ErrorMessage {
        for (int i = 1; i < 10 ; i++) {
            boolean found = true;
            for (Vehicle vehicle : vehicleList) {
                if (Integer.parseInt(vehicle.getTicketNo()) == i) {
                    found = false;
                    break;
                }
            }
            if (found) return String.valueOf(i);
        }
        throw new ErrorMessage("Garage is full!", 400);
    }

    public static List<Slot> generateSlots(List<Slot> slots, Vehicle vehicle, VehicleType vehicleType) throws ErrorMessage {
        int needSize = vehicleType.getSize() + vehicleType.getSpace();
        int counter = 0;
        List<Slot> newSlots = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            boolean control = false;
            for (Slot slot : slots) {
                if (Integer.parseInt(slot.getSlotNo()) == i) {
                    control = true;
                    break;
                }

            }
            if (control) continue;
            counter = counter + 1;
            Slot newSlot = new Slot();
            newSlot.setTicketNo(vehicle.getTicketNo());
            newSlot.setSlotNo(String.valueOf(i));
            newSlot.setVehicle(vehicle);
            newSlots.add(newSlot);
            if (counter == needSize) {
                return newSlots;
            }

        }
        throw new ErrorMessage("Garage is full!", 400);
    }
}
