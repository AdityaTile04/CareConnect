package com.project.careconnect.controller;

import com.project.careconnect.dto.DoctorRequestDTO;
import com.project.careconnect.dto.DoctorResponseDTO;
import com.project.careconnect.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable int id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping("/doctor")
    public ResponseEntity<DoctorResponseDTO> addDoctor(@RequestBody DoctorRequestDTO doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@RequestBody DoctorRequestDTO doctor, @PathVariable int id) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctor, id));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int id) {
         doctorService.deleteDoctor( id );
         return ResponseEntity.ok("Doctor deleted successfully");
    }
}
