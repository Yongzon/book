package library.log.system;

import java.util.Scanner;

public class Patrons {

    public void addPatron() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("\nEnter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Contact Number: ");
        String contactNumber = sc.nextLine();
        System.out.print("Library Card Number: ");
        String cardNumber = sc.nextLine();

        String sql = "INSERT INTO patrons (first_name, last_name, contact_number, card_number) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, firstName, lastName, contactNumber, cardNumber);
    }

    public void viewPatrons() {
        String query = "SELECT * FROM patrons";
        String[] headers = {"Patron ID", "First Name", "Last Name", "Contact Number", "Library Card Number"};
        String[] columns = {"patron_id", "first_name", "last_name", "contact_number", "card_number"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }

    public void updatePatron() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("\nEnter Patron ID to Update: ");
        int id = sc.nextInt();

        while ((conf.getSingleValue("SELECT patron_id FROM patrons WHERE patron_id = ?", id)) == 0) {
            System.out.println("Selected Patron ID doesn't exist!");
            System.out.print("Enter Patron ID again: ");
            id = sc.nextInt();
        }
        
        System.out.print("New Contact Number: ");
        String contactNumber = sc.nextLine();

        String sql = "UPDATE patrons SET contact_number = ? WHERE patron_id = ?";
        conf.updateRecord(sql, contactNumber, id);
    }

    public void deletePatron() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("\nEnter Patron ID to Delete: ");
        int id = sc.nextInt();

        while ((conf.getSingleValue("SELECT patron_id FROM patrons WHERE patron_id = ?", id)) == 0) {
            System.out.println("Selected Patron ID doesn't exist!");
            System.out.print("Enter Patron ID again: ");
            id = sc.nextInt();
        }

        String sql = "DELETE FROM patrons WHERE patron_id = ?";
        conf.deleteRecord(sql, id);
    }

    public void patronMenu() {
        Scanner sc = new Scanner(System.in);
        Patrons patron = new Patrons();

        int choice;
        boolean exit = true;

        do {
            System.out.println("\n------------ LIBRARY PATRON MANAGEMENT -------------");
            System.out.println("1. Add Patron");
            System.out.println("2. View Patrons");
            System.out.println("3. Update Patron");
            System.out.println("4. Delete Patron");
            System.out.println("5. Exit");
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    patron.addPatron();
                    break;
                case 2:
                    patron.viewPatrons();
                    break;
                case 3:
                    patron.viewPatrons();
                    patron.updatePatron();
                    break;
                case 4:
                    patron.viewPatrons();
                    patron.deletePatron();
                    break;
                case 5:
                    System.out.print("Exit selected...type yes to continue: ");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (exit);
    }
}
