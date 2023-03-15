// Write your code here

package com.example.school.repository;

import com.example.school.model.Student;
import java.util.*;



public interface StudentRepository{

    public ArrayList<Student> getAllStudents();

    public Student addnewStudent(Student newStudent);

     public String addnewStudentsList(ArrayList<Student> newStudentsList);

    public Student getStudentById(int studentId);

    public Student modifyStudentById(int studentId, Student newStudentDetails);

    public void deleteStudentById(int studentId);  

}