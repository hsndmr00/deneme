package com.example.garageWithSpring.service;

import com.example.garageWithSpring.model.Slot;
import com.example.garageWithSpring.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public List<Slot> findAll() {
        return slotRepository.findAll();
    }

    public Slot save(Slot slot) {
        return slotRepository.save(slot);
    }

    public void saveAll(List<Slot> slots) {
        slotRepository.saveAll(slots);
    }

    public void deleteById(String id) {
        slotRepository.deleteById(id);
    }

    public Slot findById(String id) {
        return slotRepository.findById(id).orElse(null);
    }
}
