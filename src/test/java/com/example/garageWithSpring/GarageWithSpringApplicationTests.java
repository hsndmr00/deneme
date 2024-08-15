package com.example.garageWithSpring;

import com.example.garageWithSpring.model.Vehicle;
import com.example.garageWithSpring.service.ParkingService;
import com.example.garageWithSpring.type.ErrorMessage;
import com.example.garageWithSpring.type.ResponseBodyMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GarageWithSpringApplicationTests {

	@Autowired
	private ParkingService parkingService;

	@Test
	void contextLoads() throws ErrorMessage {
		Vehicle vehicle = new Vehicle();
		vehicle.setPlateCode("34-AA-12");
		vehicle.setType("CAR");
		vehicle.setColor("BLUE");

		// ErrorMessage hatasını bekliyoruz
		ErrorMessage thrown = assertThrows(ErrorMessage.class, () -> {
			parkingService.parkVehicle(vehicle);
		});

		// Hata mesajının doğru olup olmadığını kontrol ediyoruz
		assertEquals("Vehicle type not found", thrown.getMessage());

	}

}
