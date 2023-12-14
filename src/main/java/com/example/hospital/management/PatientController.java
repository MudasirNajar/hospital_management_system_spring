package com.example.hospital.management;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    HashMap<Integer, Patient> patientDb = new HashMap<>();

    @PostMapping("/addViaParameter")
    public String addPatient(@RequestParam("patientId") Integer patientId,
                             @RequestParam("name") String name,
                             @RequestParam("age") Integer age,
                             @RequestParam("disease") String disease) {
        Patient patient = new Patient(patientId, name, disease, age);
        patientDb.put(patientId, patient);
        return "Patient Added Sucessfully";
    }

    @PostMapping("/addViaRequestBody")
    public String addPatient(@RequestBody Patient patient) {
        int key = patient.getPatientId();
        patientDb.put(key, patient);
        return "Patient Added Succesfully Via RequestBody";
    }

    @GetMapping("/getInfo")
    public Patient getPatient(@RequestParam("patientId") Integer patientId) {
        Patient patient = patientDb.get(patientId);
        return patient;
    }

    @GetMapping("/getInfoViaPathVaraiable/{patientId}")
    public Patient getPatientInfo(@PathVariable("patientId") Integer patientId) {
        Patient patient = patientDb.get(patientId);
        return patient;
    }

    @GetMapping("getPatients/{age}/{disease}")
    public List<Patient> getPatients(@PathVariable("age") Integer age,
                                     @PathVariable("disease") String disease) {
        List<Patient> patients = new ArrayList<>();
        for (Patient p : patientDb.values()) {
            if (p.getAge() > age && p.getDisease().equals(disease)) {
                patients.add(p);
            }
        }
        return patients;
    }

    @GetMapping("/getByName")
    public Patient getPatientByName(@RequestParam("name") String name) {
        for (Patient p : patientDb.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        for (Patient p : patientDb.values()) {
            patients.add(p);
        }
        return patients;
    }


    @GetMapping("/getPatientsListGreaterThanAge")
    public List<Patient> getPatientsGreaterThanAge(@RequestParam("age") Integer age) {
        List<Patient> patients = new ArrayList<>();
        for (Patient p : patientDb.values()) {
            if (p.getAge() > age) {
                patients.add(p);
            }
        }
        return patients;
    }

    @PutMapping("/updateDisease")
    public String updateDisease(@RequestParam("patientId") Integer patientId,
                                @RequestParam("disease") String disease) {

        if (patientDb.containsKey(patientId)) {
            Patient patient = patientDb.get(patientId);
            patient.setDisease(disease);
            patientDb.put(patientId, patient);
            return "Updated patient successfully";
        } else {
            return "Patient doesn't exist";
        }
    }

    @PutMapping("/updateDetails")
    public String updatePatientDetails(@RequestBody Patient patient) {
        int key = patient.getPatientId();

        if (patientDb.containsKey(key)) {
            patientDb.put(key, patient);
            return "Updated patient successfully";
        } else {
            return "Patient doesn't exist";
        }
    }

    @DeleteMapping("/delete")
    public String deletePatient(@RequestParam("patientId") Integer patientId) {
        patientDb.remove(patientId);
        return "Deleted patient successfully";
    }
}
