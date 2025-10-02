package com.project.careconnect.controller;

import com.project.careconnect.dto.AppointmentRequestDTO;
import com.project.careconnect.dto.AppointmentResponseDTO;
import com.project.careconnect.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> bookAppointment(@RequestBody AppointmentRequestDTO requestDTO) {
        return ResponseEntity.ok(appointmentService.bookAppointment( requestDTO ));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        return ResponseEntity.ok( appointmentService.getAllAppointments() );
    }
}
