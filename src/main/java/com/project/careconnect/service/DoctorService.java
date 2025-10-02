package com.project.careconnect.service;


import com.project.careconnect.dto.DoctorRequestDTO;
import com.project.careconnect.dto.DoctorResponseDTO;
import com.project.careconnect.model.Doctor;
import com.project.careconnect.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    private DoctorResponseDTO mapToDTO(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getPhone()
        );
    }

    private Doctor mapToEntity(DoctorRequestDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName( dto.getName() );
        doctor.setSpecialization( dto.getSpecialization() );
        doctor.setPhone( dto.getPhone() );
        return doctor;
    }
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DoctorResponseDTO addDoctor(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = mapToEntity( doctorRequestDTO );
        Doctor savedDoctor = doctorRepo.save( doctor );
        return mapToDTO( savedDoctor );
    }

    public DoctorResponseDTO getDoctorById(int id) {
        Doctor doctor = doctorRepo.findById( id )
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return mapToDTO( doctor );
    }

    public DoctorResponseDTO updateDoctor(DoctorRequestDTO doctorRequestDTO, int id) {
        return doctorRepo.findById( id )
                .map(doc -> {
                    doc.setName( doctorRequestDTO.getName() );
                    doc.setSpecialization( doctorRequestDTO.getSpecialization() );
                    doc.setPhone( doctorRequestDTO.getPhone() );
                    Doctor updatedDoctor = doctorRepo.save( doc );
                    return mapToDTO( updatedDoctor );
                })
                .orElseThrow(() -> new RuntimeException("Error while updating doctor"));
    }

    public void deleteDoctor(int id) {
        doctorRepo.deleteById(id);
    }
}

