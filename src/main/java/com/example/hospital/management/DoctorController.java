package com.example.hospital.management;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    HashMap<Integer, Doctor> doctorDb = new HashMap<>();

    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor) {
        int key = doctor.getDoctorId();
        doctorDb.put(key, doctor);
        return "Doctor added successfully";
    }
}
