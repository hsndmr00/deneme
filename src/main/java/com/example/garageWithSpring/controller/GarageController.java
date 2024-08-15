package com.example.garageWithSpring.controller;

import com.example.garageWithSpring.model.Vehicle;
import com.example.garageWithSpring.model.VehicleType;
import com.example.garageWithSpring.service.*;
import com.example.garageWithSpring.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/garage")
public class GarageController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private StatusService getStatusService;
    @Autowired
    private VehicleTypeService vehicleTypeService;


    @PostMapping(value = "/parking")
    public ResponseEntity<ResponseBodyMessage> parking(@RequestBody Vehicle vehicle) {
        try {
            ResponseBodyMessage responseMessage = parkingService.parkVehicle(vehicle);
            return ResponseEntity.ok(responseMessage);
        } catch (ErrorMessage error) {
            return ResponseEntity.status(error.getStatusCode()).body(new ResponseBodyMessage(error.getMessage()));
        }
    }

    @PutMapping("/updateVehicle/{id}")
    public ResponseEntity<ResponseBodyMessage> updateVehicleType(@PathVariable String id, @RequestBody UpdateVehicleInput input) {
        try {
            ResponseBodyMessage responseMessage = parkingService.updateVehicle(id, input);
            return ResponseEntity.ok(responseMessage);
        } catch (ErrorMessage error) {
            return ResponseEntity.status(error.getStatusCode()).body(new ResponseBodyMessage(error.getMessage()));
        }

    }

    @PostMapping("/leave")
    public ResponseEntity<ResponseBodyMessage> leave (@RequestBody LeaveInputModel inputModel) {
        try {
            ResponseBodyMessage responseMessage = leaveService.leaveVehicle(inputModel.getTicketNo());
            return ResponseEntity.ok(responseMessage);
        } catch (ErrorMessage error) {
            return ResponseEntity.status(error.getStatusCode()).body(new ResponseBodyMessage(error.getMessage()));
        }
    }

    @GetMapping("/getStatus")
    public ResponseEntity<?> getStatus() {
        try {
            List<Vehicle> vehicles = vehicleService.findAll();
            List<VehicleType> vehicleTypes = vehicleTypeService.findAll();
            List<GetStatusOutputModel> getStatusOutputModels = getStatusService.getVehiclesStatus(vehicles, vehicleTypes);
            FileService fileService = new FileService();
            fileService.writeToFile("./src/main/resources/files/status.json", getStatusOutputModels);
            return ResponseEntity.ok(getStatusOutputModels);
        } catch (ErrorMessage error) {
            return ResponseEntity.status(error.getStatusCode()).body(new ResponseBodyMessage(error.getMessage()));
        }

    }
}
