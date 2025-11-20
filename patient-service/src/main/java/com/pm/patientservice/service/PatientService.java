package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.patientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepo patientRepo;
    @Autowired
    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }

    //get patients
    public List<PatientResponseDTO> getPatient(){
        List<Patient> patients = patientRepo.findAll();
        return patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
    }

    //create patient
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepo.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepo.save(PatientMapper.toModedl(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    //update patient
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepo.findById(id).orElseThrow( ()-> new patientNotFoundException("Patient not found with ID: "+ id));
        if(patientRepo.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id )){
            throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        Patient updatedPatient = patientRepo.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    //Delete by id
    public void deletePatient(UUID id){
        patientRepo.deleteById(id);
    }
}
