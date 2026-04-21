package com.mickey.service;

import com.mickey.DAO.StudentDAO;
import com.mickey.model.Student;

import java.util.ArrayList;

public class StudentService {
    StudentDAO dao=new StudentDAO();

    public Boolean addStudent(Student student){
        if(student.getAge()<0) {
            return false;
        }

        return dao.addStudent(student);

    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students=dao.getAllStudents();

        return students;

    }

    public Boolean updateStudent(Student student){

        return dao.updateStudent(student);
    }

    public Boolean deleteStudent(int id){
            return dao.deleteStudent(id);

    }


    public Boolean bulkInsert(int num) {
        return dao.bulkInsert(num);
    }

    public Boolean clearTable() {
        return dao.clearTable();
    }
}
