/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

 package com.example.school.controller;

 import org.springframework.web.bind.annotation.*;
 import org.springframework.beans.factory.annotation.Autowired;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;
 import java.util.*;

@RestController

public class StudentController{

    @Autowired

    public StudentH2Service studentService;

    @GetMapping("/students")

    public  ArrayList<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("students/{studentId}")

    public Student getStudentById(@PathVariable("studentId")int studentId){

        return studentService.getStudentById(studentId);
    }

    @PostMapping("/students")

    public Student addnewStudent(@RequestBody Student newStudent){
        return studentService.addnewStudent(newStudent);
    }


    @DeleteMapping("/students/{studentId}")

    public void deleteStudentById(@PathVariable("studentId")int studentId){

        studentService.deleteStudentById(studentId);
    }

    @PutMapping("/students/{studentId}")

    public Student modifyStudentById(@PathVariable("studentId")int studentId, @RequestBody Student newStudentDetails){

        return  studentService.modifyStudentById(studentId, newStudentDetails);
    }

    @PostMapping("/students/bulk")

    public String addnewStudentsList(@RequestBody ArrayList<Student> newStudentsList ){

         String size = studentService.addnewStudentsList(newStudentsList);

        return ("Successfully added "+size+" students");
    }



}