package com.project.careconnect.service;

import com.project.careconnect.model.Patient;
import com.project.careconnect.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient addPatient(Patient patient) {
        return patientRepo.save( patient );
    }

    public Patient getPatientById(int id) {
        return patientRepo.findById( id )
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));
    }

    public Patient updatePatient(Patient patient, int id) {
        return patientRepo.findById( id )
                .map( p -> {
                    p.setName( patient.getName() );
                    p.setAge( patient.getAge() );
                    p.setGender( patient.getGender() );
                    p.setPhone( patient.getPhone() );
                    return patientRepo.save( p );
                } ).orElseThrow(() -> new RuntimeException("Error while updating system"));
    }

    public void deletePatient(int id) {
         patientRepo.deleteById( id );
    }
}
