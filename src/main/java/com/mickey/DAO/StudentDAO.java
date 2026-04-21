package com.mickey.DAO;

import com.mickey.model.Student;
import com.mickey.util.ConnectionPool;
import com.mickey.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {


    public Boolean addStudent(Student student){
        String query="INSERT INTO students (name,age) VALUES (?,?)";

        Connection conn=null;
        PreparedStatement ps=null;
        try{ conn= ConnectionPool.getConnection();
             ps=conn.prepareStatement(query);

            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            return ps.executeUpdate()>0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try { if (ps != null) ps.close(); } catch (Exception ignored) {}
                ConnectionPool.releaseConnection(conn);
            }
        }


    }

    public Boolean bulkInsert(int num) {
        String query = "INSERT INTO students (name,age) values (?,?)";
        Connection conn = null;
        PreparedStatement ps = null;

        long start = System.currentTimeMillis();

        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(query);

            for (int i = 1; i <= num; i++) {
                ps.setString(1, "Bulk " + i);
                ps.setInt(2, 20 + (i % 10));
                ps.addBatch();
            }

            ps.executeBatch();
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("Batch Insert Time: " + (end - start) + " ms");
            conn.setAutoCommit(true);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}

            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (Exception ignored) {}

            if (conn != null) ConnectionPool.releaseConnection(conn);
        }


    }

    public ArrayList<Student> getAllStudents(){
        String query="Select * from students";
        ArrayList<Student> student=new ArrayList<>();

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{ conn=ConnectionPool.getConnection();
             stmt=conn.createStatement();
             rs=stmt.executeQuery(query);

            while (rs.next()){

                student.add(new Student(rs.getInt("id"), rs.getString("name"),rs.getInt("age")));
            }
            return student;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();}
        finally {
            if (conn != null) {
                try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
                try { if (rs != null) rs.close(); } catch (Exception ignored) {}
                ConnectionPool.releaseConnection(conn);
            }
        }

    }

    public Boolean updateStudent(Student student){
        String query="UPDATE students SET name=?,age=? WHERE id=?";

        Connection conn=null;
        PreparedStatement ps=null;
        try{ conn= ConnectionPool.getConnection();
             ps=conn.prepareStatement(query);


            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            ps.setInt(3,student.getId());
            return ps.executeUpdate()>0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;}
        finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            if (conn != null) {
                ConnectionPool.releaseConnection(conn);
            }
        }

    }

    public Boolean deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try { if (ps != null) ps.close();} catch (Exception ignored) {}
            if (conn != null) {
                ConnectionPool.releaseConnection(conn);
            }
        }

    }


    public Boolean clearTable() {
        String query="TRUNCATE TABLE students RESTART IDENTITY ";
        Connection conn=null;
        PreparedStatement ps=null;
        try{
            conn= ConnectionPool.getConnection();
            ps=conn.prepareStatement(query);
            ps.execute();
            return true;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }finally{
            try { if (ps != null) ps.close();} catch (Exception ignored) {}
            if (conn != null) {
                ConnectionPool.releaseConnection(conn);
            }
        }

    }
}
