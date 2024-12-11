package library.log.system;

import java.util.Scanner;

public class LibraryLogSystem {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int chose, act;
        boolean exit = true;
do{ 
        System.out.println("\n------ Flight Reservation System ------");
        System.out.println("| 1. FLIGHT                            |");
        System.out.println("| 2. PASSENGER                         |");
        System.out.println("| 3. BOOKING                           |");
        System.out.println("| 4. EXIT                              |");
        System.out.println("----------------------------------------");
        System.out.print("Enter Choice: ");
        
        if(sc.hasNextInt()){
            act = sc.nextInt();
            
            switch(act){
            case 1:
                Book bk = new Book();
                bk.libraryMenu();
            break;
              
            case 2:
                Patrons ps = new Passenger();
                ps.passRec();
                break;

            case 3:
                Booking bkg = new Booking();
                bkg.bookingRec();
                break;
                
            case 4:
                System.out.print("Do you want to exit? (yes/no): ");
                    String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                    exit = false;
                    }
                break;
                
                default:
                    System.out.println("Error Choice!");
            }
            
        }else{
            System.out.println("Invalid input. Please enetr a valid number");
            }
        } while(exit);
        System.out.println("Thank you for using the System");
    }
    
}
