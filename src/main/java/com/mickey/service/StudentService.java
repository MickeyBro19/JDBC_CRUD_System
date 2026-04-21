package com.mickey.service;

import com.mickey.DAO.StudentDAO;
import com.mickey.model.Student;

import java.util.ArrayList;

public class StudentService {
    StudentDAO dao=new StudentDAO();

    public Boolean AddStudent(Student student){
        if(student.getAge()<0) {
            return false;
        }

        return dao.addStudent(student);

    }

    public ArrayList<Student> GetAllStudents() {
        ArrayList<Student> students=dao.getAllStudents();

        return students;

    }

    public Boolean UpdateStudent(Student student){

        return dao.updateStudent(student);
    }

    public Boolean DeleteStudent(int id){
            return dao.deleteStudent(id);

    }


}
