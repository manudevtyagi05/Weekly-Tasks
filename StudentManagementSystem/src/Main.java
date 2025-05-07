import java.util.ArrayList;
import java.util.Scanner;

class Student{
    int id;
    String name;
    int marks;

    public Student(int id, String name, int marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks){
        this.marks = marks;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student added successfully");
    }

    public void deleteStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                System.out.println("Student removed");
            } else {
                System.out.println("Student not found");
            }
        }
    }

    public void updateMarks(int id, int newMarks) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setMarks(newMarks);
                System.out.println("Marks updated");
            } else {
                System.out.println("Student not found");
            }
        }
    }

    public void searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println(s);
            } else {
                System.out.println("Student not found");
            }
        }
    }
}

public class Main {
    public static void main(String[] args){
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Student Management Menu");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Update Marks");
        System.out.println("4. Search Student by ID");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        choice = sc.nextInt();

        switch (choice){
            case 1:
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Marks: ");
                int marks = sc.nextInt();
                sms.addStudent(new Student(id, name, marks));
                break;

            case 2:
                System.out.print("Enter ID to delete: ");
                int delId = sc.nextInt();
                sms.deleteStudent(delId);
                break;

            case 3:
                System.out.print("Enter ID to update: ");
                int upId = sc.nextInt();
                System.out.print("Enter new marks: ");
                int newMarks = sc.nextInt();
                sms.updateMarks(upId, newMarks);
                break;

            case 4:
                System.out.print("Enter ID to search: ");
                int searchId = sc.nextInt();
                sms.searchStudent(searchId);
                break;

            case 5:
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}