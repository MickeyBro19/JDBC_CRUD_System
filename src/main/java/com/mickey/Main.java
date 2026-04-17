package com.mickey;

import com.mickey.DAO.StudentDAO;
import com.mickey.model.Student;
import com.mickey.service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Scanner sc = new Scanner(System.in);
        StudentService service=new StudentService();

        while (true) {
            System.out.println("1. Add  2. View  3. Update  4. Delete  5. Exit");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: {
                    System.out.println("Enter student details");
                    System.out.println("Enter name");
                    String name = sc.nextLine();
                    System.out.println("Enter Age");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.println( service.AddStudent(new Student(name, age))?"Student Added successfully":"Error adding student");
                    break;
                }
                case 2: {
                    System.out.println("Getting Student Details");
                    ArrayList<Student> students=  service.GetAllStudents();
                    if(students.isEmpty()){
                        System.out.println("No students in DB");
                        break;
                    }
                    students.stream()
                            .forEach(student -> System.out.println(
                                    "id: " + student.getId() +
                                            " , name: " + student.getName() +
                                            " , age: " + student.getAge()
                            ));
                    break;
                }
                case 3: {
                    System.out.println("Update student Details");
                    System.out.println("Enter id");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter name");
                    String name = sc.nextLine();
                    System.out.println("Enter age");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.println( service.UpdateStudent(new Student(id, name,age))?"Student Updated successfully":"Error updating Student");
                    break;
                }
                case 4: {
                    System.out.println("Delete Student");
                    System.out.println("Enter id");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println( service.DeleteStudent(id)?"Student Removed successfully":"Error Removing Student");
                    break;
                }
                case 5:
                    System.out.println("Exiting DB");
                    return;
            }
        }    }
}
