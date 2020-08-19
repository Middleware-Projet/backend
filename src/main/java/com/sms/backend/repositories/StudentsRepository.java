package com.sms.backend.repositories;


import com.sms.backend.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<Student,Integer> {
}