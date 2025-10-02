package com.project.careconnect.repository;

import com.project.careconnect.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
}
