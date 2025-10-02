package com.project.careconnect.service;

import com.project.careconnect.dto.PatientRequestDTO;
import com.project.careconnect.dto.PatientResponseDTO;
import com.project.careconnect.model.Patient;
import com.project.careconnect.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    private PatientResponseDTO mapToDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getPhone()
        );
    }

    private Patient mapToEntity(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setName( dto.getName() );
        patient.setAge( dto.getAge() );
        patient.setPhone( dto.getPhone() );
        return patient;
    }

    public List<PatientResponseDTO> getAllPatients() {
        return patientRepo.findAll()
                .stream()
                .map( this::mapToDTO )
                .collect( Collectors.toList() );
    }

    public PatientResponseDTO getPatientById(int id) {
        Patient patient = patientRepo.findById( id )
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return mapToDTO( patient );
    }
    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = mapToEntity( patientRequestDTO );
        Patient savedPatient = patientRepo.save( patient );
        return mapToDTO( savedPatient );
    }

    public PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO, int id) {
        return patientRepo.findById( id )
                .map(patient -> {
                    patient.setName( patientRequestDTO.getName() );
                    patient.setAge( patientRequestDTO.getAge() );
                    patient.setPhone(patientRequestDTO.getPhone());
                    Patient updatedPatient = patientRepo.save( patient );
                    return mapToDTO( updatedPatient );
                })
                .orElseThrow(() -> new RuntimeException("Error while updating Patient"));
    }

    public void deletePatient(int id) {
         patientRepo.deleteById( id );
    }

}
