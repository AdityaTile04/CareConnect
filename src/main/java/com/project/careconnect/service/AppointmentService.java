package com.project.careconnect.service;

import com.project.careconnect.dto.AppointmentRequestDTO;
import com.project.careconnect.dto.AppointmentResponseDTO;
import com.project.careconnect.model.Appointment;
import com.project.careconnect.model.Doctor;
import com.project.careconnect.model.Patient;
import com.project.careconnect.repository.AppointmentRepo;
import com.project.careconnect.repository.DoctorRepo;
import com.project.careconnect.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    private AppointmentResponseDTO mapToDTO(Appointment appointment) {
        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getDoctor().getName(),
                appointment.getPatient().getName(),
                appointment.getAppointmentTime()
        );
    }

    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO requestDTO) {
        Doctor doctor = doctorRepo.findById( requestDTO.getDoctorId() )
                .orElseThrow( () -> new RuntimeException("Doctor not found") );

        Patient patient = patientRepo.findById( requestDTO.getPatientId() )
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setDoctor( doctor );
        appointment.setPatient( patient );
        appointment.setAppointmentTime( requestDTO.getAppointmentTime() );

        Appointment savedAppointment = appointmentRepo.save( appointment );
        return mapToDTO( savedAppointment );
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepo.findAll().stream()
                .map( this::mapToDTO )
                .collect( Collectors.toList() );
    }
}
