package com.project.careconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {
    private int id;
    private String name;
    private String specialization;
    private String phone;
}
