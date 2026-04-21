package com.mickey;
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

        /* Simulating Load Test (Multi-threaded)

        for (int i = 1; i <= 6; i++) {
            int threadId = i;

            new Thread(() -> {
                try {
                    System.out.println("Thread " + threadId + " requesting connection...");

                    Connection conn = ConnectionPool.getConnection();

                    System.out.println("Thread " + threadId + " got connection!");

                    // Simulate DB work
                    Thread.sleep(3000);

                    ConnectionPool.releaseConnection(conn);
                    System.out.println("Thread " + threadId + " released connection");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }*/

        while (true) {
            System.out.println("1. Add  2. View  3. Update  4. Delete  5. Exit  6. Bulk Insert  7. Clear Table");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: {
                    System.out.println("Enter student details");
                    System.out.println("Enter name");
                    String name = sc.nextLine();
                    System.out.println("Enter Age");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.println( service.addStudent(new Student(name, age))?"Student Added successfully":"Error adding student");
                    break;
                }
                case 2: {
                    System.out.println("Getting Student Details");
                    ArrayList<Student> students=  service.getAllStudents();
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
                    System.out.println( service.updateStudent(new Student(id, name,age))?"Student Updated successfully":"Error updating Student");
                    break;
                }
                case 4: {
                    System.out.println("Delete Student");
                    System.out.println("Enter id");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println( service.deleteStudent(id)?"Student Removed successfully":"Error Removing Student");
                    break;
                }
                case 5: {
                    System.out.println("Exiting DB");
                    return;
                }
                case 6: {
                    System.out.println("Bulk Insert");
                    System.out.println("Enter Number of students to bulk add");
                    int num = Integer.parseInt(sc.nextLine());
                    System.out.println( service.bulkInsert(num)?"Entered Students Successfully":"Error Inserting data to Student");

                    break;
                }
                case 7: {
                    System.out.println("Clear Table");
                    System.out.println( service.clearTable()?"Table Cleared Successfully":"Error Clearing Student");
                    break;
                }
            }
        }
    }
}
