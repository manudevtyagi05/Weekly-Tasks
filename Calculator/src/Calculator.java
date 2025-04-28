
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Menu");
        System.out.println("ADD");
        System.out.println("SUB");
        System.out.println("MUL");
        System.out.println("DIV");

        System.out.println("Enter 2 number");
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Enter option in Words");
        String option = sc.next();

        switch (option) {
            case "ADD":
                System.out.println(a + b);
                break;
            case "SUB":
                System.out.println(a - b);
                break;
            case "MUL":
                System.out.println(a * b);
                break;
            case "DIV":
                System.out.println(a / b);
                break;
            default:
                System.out.println("Invalidate Option");
        }
    }
}
