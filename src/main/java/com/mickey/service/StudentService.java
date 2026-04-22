package com.mickey.service;

import com.mickey.DAO.StudentDAO;
import com.mickey.model.Student;
import com.mickey.util.AppLogger;

import java.util.ArrayList;

public class StudentService {
    StudentDAO dao=new StudentDAO();

    public Boolean addStudent(Student student){
        if(student.getAge()<0) {
            AppLogger.error("Age can't be negative "+ student.getName());
            return false;
        }
        AppLogger.log("Processing addStudent request for: " + student.getName());
        boolean result = dao.addStudent(student);

        if(result){
            AppLogger.log("Student added successfully: " + student.getName());
        }else{
            AppLogger.error("Failed to add student: " + student.getName());
        }
        return result;

    }

    public ArrayList<Student> getAllStudents() {
        AppLogger.log("Processing getAllStudents request");
        ArrayList<Student> students = dao.getAllStudents();
        AppLogger.log("Fetched " + students.size() + " students");
        return students;

    }

    public Boolean updateStudent(Student student){
        AppLogger.log("Processing updateStudent request for: " + student.getName());
        boolean result = dao.updateStudent(student);

        if(result){
            AppLogger.log("Student updated successfully: " + student.getName());
        }else{
            AppLogger.error("Failed to update student: " + student.getName());
        }
        return result;
    }

    public Boolean deleteStudent(int id){
        AppLogger.log("Processing deleteStudent request for id: " + id);

        boolean result = dao.deleteStudent(id);

        if(result){
            AppLogger.log("Student deleted successfully: " + id);
        }else{
            AppLogger.error("Failed to delete Student with id: " + id);
        }

        return result;


    }


    public Boolean bulkInsert(int num) {
        AppLogger.log("Starting bulk insert of " + num + " students");
        boolean result = dao.bulkInsert(num);

        if(result){
            AppLogger.log("Bulk insert completed successfully");
        }else{
            AppLogger.error("Bulk insert failed");
        }
        return result;
    }

    public Boolean clearTable() {
        AppLogger.log("Starting clear table of students");
        boolean result = dao.clearTable();

        if(result){
            AppLogger.log("Clear Table completed successfully");
        }else{
            AppLogger.error("Clear Table failed");
        }
        return result;
    }
}
