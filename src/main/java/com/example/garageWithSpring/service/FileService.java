package com.example.garageWithSpring.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.garageWithSpring.type.ErrorMessage;
import com.example.garageWithSpring.type.GetStatusOutputModel;

public class FileService {
    private StringBuilder fileToString(List<GetStatusOutputModel> content) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (!content.isEmpty()) {
            result.append("\n");
        }
        for (GetStatusOutputModel getStatusOutputModel : content) {
            result.append("  {\n");
            result.append("   \"color \": \"").append(getStatusOutputModel.getColor()).append("\",");
            result.append("\n");
            result.append("   \"ticketNo \": \"").append(getStatusOutputModel.getTicketNo()).append("\",");
            result.append("\n");
            result.append("   \"plateCode \": \"").append(getStatusOutputModel.getPlateCode()).append("\",");
            result.append("\n");
            result.append("   \"vehicleType \": \"").append(getStatusOutputModel.getVehicleType()).append("\",");
            result.append("\n");
            result.append("   \"slots \": [");
            for (String slot : getStatusOutputModel.getSlots()) {
                result.append("\"").append(slot).append("\"").append(",");
            }
            result.deleteCharAt(result.length() - 1);
            result.append("]\n");
            result.append("  },\n");
        }
        if (!content.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
            result.append("\n");
        }

        result.append("]");
        return result;
    }

    public void writeToFile(String filePath, List<GetStatusOutputModel> content) throws ErrorMessage {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, fileToString(content).toString().getBytes());
            System.out.println("Written file: " + filePath);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + filePath);
        }

    }
}
