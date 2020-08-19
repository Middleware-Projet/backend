package com.sms.backend.controllers;

import com.sms.backend.models.Student;
import com.sms.backend.repositories.StudentsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/sms/api/")
public class StudentController {
    private final StudentsRepository studentsRepository;

    public StudentController(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @GetMapping(path = "all")
    public @ResponseBody
    Iterable<Student> getStudents() {
        return studentsRepository.findAll();
    }

    @PostMapping("add")
    public @ResponseBody
    String createStudent(@RequestBody Student student) {
        studentsRepository.save(student);
        return "Success";
    }

    @PutMapping(path = "{id}")
    public @ResponseBody
    String updatePost(@PathVariable Integer id, @RequestBody Student newStudent) {
        Optional<Student> optionalStudent = studentsRepository.findById(id);

        String updateSuccess = "Failed";

        if (optionalStudent.isPresent()) {
            Student oldStudent = optionalStudent.get();

            if (newStudent.getName() != null) {
                oldStudent.setName(newStudent.getName());
            }

            if (newStudent.getAge() != null) {
                oldStudent.setAge(newStudent.getAge());
            }

            if (newStudent.getGender() != null) {
                oldStudent.setGender(newStudent.getGender());
            }

            if (newStudent.getCourse() != null) {
                oldStudent.setCourse(newStudent.getCourse());
            }

            studentsRepository.save(oldStudent);

            updateSuccess = "Success";
        }

        return updateSuccess;

    }

    @DeleteMapping(path = "{id}")
    public @ResponseBody
    String deleteStudent(@PathVariable Integer id) {
        boolean student = studentsRepository.existsById(id);
        if (student) {
            studentsRepository.deleteById(id);
            return "Success";
        } else {
            return "Failed";
        }
    }
}