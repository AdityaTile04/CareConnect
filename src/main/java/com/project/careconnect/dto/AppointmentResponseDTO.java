package com.project.careconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {
    private int id;
    private String doctorName;
    private String patientName;
    private LocalDateTime appointmentTime;
}
