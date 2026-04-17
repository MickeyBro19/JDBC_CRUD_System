package com.mickey.DAO;

import com.mickey.model.Student;
import com.mickey.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {


    public Boolean addStudent(Student student){
        String query="INSERT INTO students (name,age) VALUES (?,?)";

        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)
            ){
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());

            return ps.executeUpdate()>0;

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return false;

    }

    public ArrayList<Student> getAllStudents(){
        String query="Select * from students";
        ArrayList<Student> student=new ArrayList<>();

        try(Connection conn=DBConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query)
        ){
            while (rs.next()){

                student.add(new Student(rs.getInt("id"), rs.getString("name"),rs.getInt("age")));
            }
            return student;
        } catch (Exception e) {
            System.out.println(e.getMessage());        }
return new ArrayList<>();
    }

    public Boolean updateStudent(Student student){
        String query="UPDATE students SET name=?,age=? WHERE id=?";

        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)
        ){

            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            ps.setInt(3,student.getId());

            return ps.executeUpdate()>0;

        } catch (Exception e) {
            System.out.println(e.getMessage());        }
        return false;
    }

    public Boolean deleteStudent(int id){
        String query="DELETE FROM students WHERE id=?";

        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)
        ){
            ps.setInt(1,id);
            return ps.executeUpdate()>0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
