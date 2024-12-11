package library.log.system;;

import java.util.Scanner;

public class Book {
    
    public void addBook() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.println("BOOK INFORMATION");
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        
        System.out.print("Enter Book Author: ");
        String author = sc.nextLine();
        
        System.out.print("Enter Book Genre: ");
        String genre = sc.nextLine();
        
        System.out.print("Enter Number of Copies: ");
        int copies = sc.nextInt();
        
        sc.nextLine(); // Consume newline
        System.out.print("Enter Book Publication Year: ");
        int year = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        String sql = "INSERT INTO books (title, author, genre, copies, year) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, title, author, genre, copies, year);
    }

    public void viewBooks() {
        String query = "SELECT * FROM books";
        String[] headers = {"Book ID", "Title", "Author", "Genre", "Copies Available", "Publication Year"};
        String[] columns = {"book_id", "title", "author", "genre", "copies", "year"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }

    public void updateBook() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Book ID to Update: ");
        int bookId = sc.nextInt();
        
        while ((conf.getSingleValue("SELECT book_id FROM books WHERE book_id = ?", bookId)) == 0) {
            System.out.println("Selected Book ID doesn't exist!");
            System.out.print("Enter Book ID again: ");
            bookId = sc.nextInt();
        }
        sc.nextLine(); // Consume newline
        System.out.print("New Number of Copies: ");
        int copies = sc.nextInt();

        String sql = "UPDATE books SET copies = ?,WHERE book_id = ?";
        conf.updateRecord(sql, copies, bookId);
    }

    public void deleteBook() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("\nEnter Book ID to Delete: ");
        int bookId = sc.nextInt();

        while ((conf.getSingleValue("SELECT book_id FROM books WHERE book_id = ?", bookId)) == 0) {
            System.out.println("Selected Book ID doesn't exist!");
            System.out.print("Enter Book ID again: ");
            bookId = sc.nextInt();
        }

        String sql = "DELETE FROM books WHERE book_id = ?";
        conf.deleteRecord(sql, bookId);
    }

    public void libraryMenu() {
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        
        int choice;
        boolean exit = true;

        do {
            System.out.println("\n------------ LIBRARY MANAGEMENT SYSTEM -------------");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------------------");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    book.addBook();
                    break;
                case 2:
                    book.viewBooks();
                    break;
                case 3:
                    book.updateBook();
                    break;
                case 4:
                    book.deleteBook();
                    break;
                case 5:
                    System.out.print("Exit selected...type yes to continue: ");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
                default:
                    System.out.println("Invalid choice, please select again.");
            }
        } while (exit);
    }
}
