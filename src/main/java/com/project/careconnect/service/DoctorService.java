package com.project.careconnect.service;

import com.project.careconnect.model.Doctor;
import com.project.careconnect.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepo.save( doctor );
    }

    public Doctor getDoctorById(int id) {
        return doctorRepo.findById( id ).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Doctor updateDoctor(Doctor doctor, int id) {
        return doctorRepo.findById( id )
                .map( doc -> {
                    doc.setName( doctor.getName() );
                    doc.setSpecialization(doctor.getSpecialization());
                    doc.setPhone( doctor.getPhone() );
                    return doctorRepo.save( doc );
                }
                ).orElseThrow(() -> new RuntimeException("Error while updating user"));
    }

    public void deleteDoctor(int id) {
         doctorRepo.deleteById( id );
    }
}

