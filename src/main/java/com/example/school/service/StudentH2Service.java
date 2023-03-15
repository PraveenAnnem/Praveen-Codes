/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here

package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import javax.validation.OverridesAttribute;

import com.example.school.repository.StudentRepository;
import com.example.school.model.*;


@Service

public class StudentH2Service implements StudentRepository{

    @Autowired 

    private JdbcTemplate db;

    @Override

    public ArrayList<Student> getAllStudents(){

        List<Student> StudentsList = db.query("SELECT * FROM student",new StudentRowMapper());

        ArrayList<Student> StudentDetails = new ArrayList<>(StudentsList);

        return StudentDetails;
    }

    @Override 

    public Student addnewStudent(Student newStudent){

        db.update("INSERT INTO student(studentName,gender,standard)values(?,?,?)",newStudent.getStudentName(),newStudent.getGender(),newStudent.getStandard());

        Student savedStudent = db.queryForObject("SELECT * FROM student where StudentName=?",new StudentRowMapper(),newStudent.getStudentName());


        return savedStudent;
    }

    @Override 

    public void deleteStudentById(int studentId){
        
          db.update("DELETE FROM student WHERE studentId =?",studentId );
    
    }

    @Override 

    public Student getStudentById(int studentId){

        try {
         Student studentDetail = db.queryForObject("SELECT * FROM student WHERE studentId=?",new StudentRowMapper(), studentId);

        return studentDetail;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override

    public Student modifyStudentById(int studentId, Student newStudentDetails){

        if(newStudentDetails.getStudentName()!=null && newStudentDetails.getGender()!=null && newStudentDetails.getStandard()!=0){
            
          db.update("UPDATE student SET studentName=?, gender=?,standard=? WHERE studentId=?", newStudentDetails.getStudentName(),
        newStudentDetails.getGender(),newStudentDetails.getStandard(),studentId);
        }
        return getStudentById(studentId);
    }
    

    @Override 

    public String addnewStudentsList(ArrayList<Student> newStudentList){

        for (int i=0;i<newStudentList.size();i++){

            db.update("INSERT INTO student (studentName,gender,standard)values(?,?,?)",newStudentList.get(i).getStudentName(),
            newStudentList.get(i).getGender(),newStudentList.get(i).getStandard());
        }
        return  String.valueOf(newStudentList.size());
    }




}

