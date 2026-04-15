package com.mickey.DAO;

import com.mickey.model.Student;
import com.mickey.util.DBConnection;

import java.sql.*;

public class StudentDAO {
    public void addStudent(Student student){
        String query="INSERT INTO students (name,age) VALUES (?,?)";

        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)
            ){
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());

            ps.executeUpdate();
            System.out.println("Student ADDED to DB");
        } catch (Exception e) {
            System.out.println(e.getMessage());        }

    }

    public void getAllStudents(){
        String query="Select * from students";

        try(Connection conn=DBConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query)
        ){
            while (rs.next()){
                System.out.println(
                                "id: " + rs.getInt("id") +
                                " , name: " + rs.getString("name") +
                                " , age: " + rs.getInt("age")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());        }

    }

    public void updateStudent(int id, String name){
        String query="UPDATE students SET name=? WHERE id=?";

        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)
        ){
            ps.setString(1,name);
            ps.setInt(2,id);

            ps.executeUpdate();
            System.out.println("Student Updated");

        } catch (Exception e) {
            System.out.println(e.getMessage());        }
    }

    public void deleteStudent(int id){
        String query="DELETE FROM students WHERE id=?";

        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(query)
        ){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Student Removed");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
