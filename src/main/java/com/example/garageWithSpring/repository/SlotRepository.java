package com.example.garageWithSpring.repository;

import com.example.garageWithSpring.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, String> {
}
