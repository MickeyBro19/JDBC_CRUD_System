package com.mickey.DAO;

import com.mickey.model.Student;
import com.mickey.util.ConnectionPool;
import com.mickey.util.AppLogger;

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
            int rows= ps.executeUpdate();
            if(rows>0){
                AppLogger.log("Student added "+ student.getName());
                return true;
            }else {
                AppLogger.error("Failed to add Student "+ student.getName());
                return false;
            }

        } catch (Exception e) {
            AppLogger.error("DB Error in addStudent: " + e.getMessage());
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
            AppLogger.log("Bulk insert completed: " + num + " records in " + (end-start) + " ms");
            conn.setAutoCommit(true);
            return true;

        } catch (Exception e) {
            AppLogger.error("DB Error in bulkInsert: " + e.getMessage());
            try {
                if (conn != null) {
                    AppLogger.error("Transaction failed. Rolling back...");
                    conn.rollback();
                };
            } catch (Exception rollbackEx) {
                AppLogger.error("Rollback failed: " + rollbackEx.getMessage());
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
            AppLogger.log("Fetched " + student.size() + " students from DB");
            return student;
        } catch (Exception e) {
            AppLogger.error("DB Error in getAllStudents: " + e.getMessage());
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
            int rows= ps.executeUpdate();
            if(rows>0){
                AppLogger.log("Student Updated "+ student.getName());
                return true;
            }else {
                AppLogger.error("Failed to update Student "+ student.getName());
                return false;
            }

        } catch (Exception e) {
            AppLogger.error("DB Error in updateStudent: " + e.getMessage());
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
            int rows= ps.executeUpdate();
            if(rows>0){
                AppLogger.log("Student deleted with id: " + id);
                return true;
            }else {
                AppLogger.error("Failed to delete Student ");
                return false;
            }

        } catch (Exception e) {
            AppLogger.error("DB Error in deleteStudent: " + e.getMessage());
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
            AppLogger.log("Table cleared successfully");
            return true;

        }catch(Exception e){
            AppLogger.error("DB Error in clearTable: " + e.getMessage());
            return false;
        }finally{
            try { if (ps != null) ps.close();} catch (Exception ignored) {}
            if (conn != null) {
                ConnectionPool.releaseConnection(conn);
            }
        }

    }
}
