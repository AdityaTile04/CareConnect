package com.project.careconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDTO {
    private int doctorId;
    private int patientId;
    private LocalDateTime appointmentTime;
}
