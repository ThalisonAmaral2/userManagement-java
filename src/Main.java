import User.User;
import database.Database;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("Choose an operation: ");
            System.out.println("1 - List all users");
            System.out.println("2 - Register new user");
            System.out.println("3 - Search an user");
            System.out.println("4 - Delete user");
            System.out.println("5 - Exit");

            int operation = scan.nextInt();
            scan.nextLine();
            switch(operation){
                case 1 -> {
                    database.displayList();
                }
                case 2 -> {
                    System.out.print("First name: ");
                    String firstName = scan.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scan.nextLine();
                    System.out.print("Age: ");
                    int age = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Email: ");
                    String email = scan.nextLine();

                    database.registerUser(firstName, lastName, age, email);
                    System.out.println("User registered successfully!");
                }
                case 3 -> {
                    System.out.print("Search by name: ");
                    String searchName = scan.nextLine();
                    database.displayUser(searchName);
                }
                case 4 -> {
                    System.out.print("Search user to be deleted by name: ");
                    String searchName = scan.nextLine();

                    database.displayUser(searchName);
                    System.out.println("Do you want to delete this user?: (y/n)");
                    String answer = scan.nextLine();

                    if(Objects.equals(answer, "y")){
                        System.out.println("Deleting user...");
                        database.removeUser(searchName);
                        System.out.println("User deleted!!");
                    }else{
                        System.out.println("Aborting deletion...");
                    }
                }
                case 5 -> {
                    System.out.println("Exiting program..");
                    running = false;
                }
                default -> {
                    System.out.println("Invalid option!");
                }
            }
        }
    }
}