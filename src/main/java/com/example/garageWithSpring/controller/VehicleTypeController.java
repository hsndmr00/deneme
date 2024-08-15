package com.example.garageWithSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.garageWithSpring.model.VehicleType;
import com.example.garageWithSpring.service.VehicleTypeService;
import com.example.garageWithSpring.type.ResponseBodyMessage;

// Join kullanarak vehicle'ın kapladığı slotlar SELECT * FROM VEHICLE v JOIN SLOT s ON v.PLATE_CODE = s.VEHICLE_PLATE_CODE
// vehicleType'a göre kapladığı slotlar SELECT * FROM VEHICLE_TYPE vt JOIN (VEHICLE v JOIN SLOT s ON v.PLATE_CODE = s.VEHICLE_PLATE_CODE) vs ON vs.TYPE = vt.NAME
// vehicle için put TAMAMLANDI
// slot için put
// getStatus'de ticketNo ve vehicleType ekle TAMAMLANDI
// getStatus service'in sonucunu dosyaya yaz TAMAMLANDI

@RestController
@RequestMapping("api/vehicleType")
public class VehicleTypeController {
    @Autowired
    private VehicleTypeService vehicleTypeService;

    @GetMapping
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeService.findAll();
    }

    @GetMapping("/{id}")
    public VehicleType getVehicleTypeById(@PathVariable String id) {
        return vehicleTypeService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseBodyMessage> createVehicleType(@RequestBody VehicleType vehicleType) {
        if (vehicleType.getSize() < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseBodyMessage("Size 0 must be greater than"));
        }
        vehicleTypeService.save(vehicleType);
        return ResponseEntity.ok(new ResponseBodyMessage("Vehicle type created successfully"));
    }

    @PutMapping("/{id}")
    public VehicleType updateVehicleType(@PathVariable String id, @RequestBody VehicleType vehicleType) {
        vehicleType.setName(id);
        return vehicleTypeService.save(vehicleType);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicleType(@PathVariable String id) {
        vehicleTypeService.deleteById(id);
    }
}
