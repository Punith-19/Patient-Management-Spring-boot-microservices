package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private PatientRepo patientRepo;
    @Autowired
    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }
    public List<PatientResponseDTO> getPatient(){
        List<Patient> patients = patientRepo.findAll();
        return patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
    }
}
