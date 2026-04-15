package com.mickey;

import com.mickey.DAO.StudentDAO;
import com.mickey.model.Student;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("1. Add  2. View  3. Update  4. Delete  5. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter student details");
                    System.out.println("Enter name");
                    String name = sc.nextLine();
                    System.out.println("Enter Age");
                    int age = sc.nextInt();
                    dao.addStudent(new Student(name, age));
                    break;
                }
                case 2: {
                    System.out.println("Getting Student Details");
                    dao.getAllStudents();
                    break;
                }
                case 3: {
                    System.out.println("Update student Details");
                    System.out.println("Enter id");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter name");
                    String name = sc.nextLine();
                    dao.updateStudent(id, name);
                    break;
                }
                case 4: {
                    System.out.println("Delete Student");
                    System.out.println("Enter id");
                    int id = sc.nextInt();
                    dao.deleteStudent(id);
                    break;
                }
                case 5:
                    System.out.println("Exiting DB");
                    return;
            }
        }    }
}
