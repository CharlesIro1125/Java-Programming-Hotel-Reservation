package Menu;

import model.Customer;
import model.IRoom;
import model.Reservations;
import model.Reservations;
import model.RoomType;

import java.time.Instant;
import java.time.Period;
import java.util.*;

public class HotelApplication {

    AdminMenu adminMenu = new AdminMenu();
    MainMenu mainMenu = new MainMenu();
    Date checkOutDate;
    Date checkInDate;
    String firstName;
    String lastName;
    String email;
    String roomNo;
    Double roomPrice;
    RoomType roomType;
    List<IRoom> findRooms;

    public void Menu() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try{
                    System.out.println("Main Menu");
                    System.out.println("1. Find and reserve a room");
                    System.out.println("2. See my reservation");
                    System.out.println("3. Crete an Account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");
                    System.out.println("Please enter your selection from 1 to 5");
                    int selection = Integer.parseInt(scanner.nextLine());

                    if(selection == 1){

                        boolean wrongDate = true;
                        while (wrongDate) {
                            try {
                                System.out.println("Enter check-in Date mm/dd/yyyy  example: 02/03/2022");
                                String checkInInput = scanner.nextLine().strip();
                                Calendar calendar1 = Calendar.getInstance();
                                int year = Integer.parseInt(checkInInput.substring(6, 9));
                                int month = Integer.parseInt(checkInInput.substring(0, 2));
                                int day = Integer.parseInt(checkInInput.substring(3, 5));
                                calendar1.clear();
                                calendar1.set(year, month - 1, day);
                                checkInDate = calendar1.getTime();
                                Date presentDate = new Date();
                                Calendar calendar3 = Calendar.getInstance();
                                calendar3.setTime(presentDate);
                                presentDate = calendar3.getTime();
                                System.out.println("present date:  " + presentDate);
                                System.out.println("checkin date:  " + checkInInput);
                                //if(presentDate.after(checkInDate)){
                                //  System.out.println("Enter a date in the future");
                                // throw new IllegalArgumentException();
                                //}
                                System.out.println("Enter check-out Date mm/dd/yyyy  example: 02/03/2022");
                                String checkOutInput = scanner.nextLine().strip();
                                Calendar calendar2 = Calendar.getInstance();
                                year = Integer.parseInt(checkOutInput.substring(6, 9));
                                month = Integer.parseInt(checkOutInput.substring(0, 2));
                                day = Integer.parseInt(checkOutInput.substring(3, 5));
                                calendar2.clear();
                                calendar2.set(year, month - 1, day);
                                checkOutDate = calendar2.getTime();
                                if (checkInDate.after(checkOutDate)) {
                                    System.out.println("Error: checkOutDate behind checkInDate");
                                    throw new IllegalArgumentException();
                                }


                                System.out.println("checkin Date: " + checkInDate + "checkOut Date: " + checkOutDate);

                            } catch (Exception e) {
                                System.out.println("\n Error - Invalid Input \n Please input a correct Date");

                            }
                                try {
                                    findRooms = mainMenu.find_rooms(checkInDate,checkOutDate);
                                }catch (Exception e){

                                }

                                if(findRooms.isEmpty()){
                                    System.out.println("\n No Available rooms");
                                    System.out.println("\n Would you like to search for a week ahead?  y/n");
                                    String getresponse = scanner.nextLine().strip();
                                    Boolean stepahead = true;
                                    while (stepahead) {
                                        if (getresponse.equals("y")) {
                                            Calendar calend1 = Calendar.getInstance();
                                            Calendar calend2 = Calendar.getInstance();
                                            calend1.setTime(checkInDate);
                                            calend2.setTime(checkOutDate);
                                            Instant checkIn = calend1.toInstant();
                                            Instant checkOut = calend2.toInstant();
                                            checkIn.plus(Period.ofDays(7));
                                            checkOut.plus(Period.ofDays(7));
                                            Date newCheckInDate = Date.from(checkIn);
                                            Date newCheckOutDate = Date.from(checkOut);
                                            try {
                                                findRooms = mainMenu.find_rooms(newCheckInDate, newCheckOutDate);
                                                stepahead = false;
                                            } catch (Exception e) {
                                                stepahead = false;
                                            }
                                        } else if (getresponse.equals("n")) {
                                            stepahead = false;
                                            Menu();
                                        }
                                    }
                                }if(findRooms.isEmpty()){
                                    System.out.println("\n No Available rooms a week ahead");
                                    Menu();
                                }
                                System.out.println("\n Available rooms");
                                for(IRoom room:findRooms){System.out.println(room);}

                                wrongDate = false;



                        }
                        System.out.println("Would you like to book a room?  Y/n");
                        System.out.println("Please enter Y or n");
                        String Input;
                        boolean bookingrunning = true;
                        while (bookingrunning) {
                            Input = scanner.nextLine().strip().toLowerCase();

                            if (Input.equals("n".strip())){
                                bookingrunning = false;
                                Menu();
                            } if (Input.equals("y".strip())){
                                bookingrunning = false;
                            } else {
                                System.out.println("Please enter Y or n");
                            }
                        }
                        boolean roomrunning = true;
                        while (roomrunning) {
                            try {
                                System.out.println("Choose a room number from Available rooms above or 0 to Exit");
                                Integer roomInput = Integer.parseInt(scanner.nextLine());
                                roomNo = roomInput.toString();
                                List<Integer> availableRooms = new ArrayList<Integer>();
                                for(IRoom room:findRooms){availableRooms.add(Integer.parseInt(room.getRoomNumber()));}
                                availableRooms.add(Integer.parseInt("0"));
                                while (!(availableRooms.contains(roomInput))){
                                    System.out.println("Please choose a correct room number above or 0 to Exit");
                                    roomInput = Integer.parseInt(scanner.nextLine());
                                    roomNo = roomInput.toString();
                                }
                                if(roomNo.equals("0")){
                                    Menu();
                                }

                                roomrunning = false;
                            }catch (Exception e){
                                System.out.println("\n Error - Invalid Input \n");
                            }
                        }
                        System.out.println("Please enter your first name");
                        Input = scanner.nextLine().strip().toUpperCase();
                        firstName = Input;
                        while (firstName.isBlank()){
                            System.out.println("Please enter your first name");
                            Input = scanner.nextLine().strip().toUpperCase();
                            firstName = Input;
                        }
                        System.out.println("Please enter your last name");
                        Input = scanner.nextLine().strip().toUpperCase();
                        lastName = Input;
                        while (lastName.isBlank()){
                            System.out.println("Please enter your last name");
                            Input = scanner.nextLine().strip().toUpperCase();
                            lastName = Input;
                        }
                        boolean wrongemail = true;
                        while (wrongemail){
                            try{
                                System.out.println("Please enter your email address");
                                Input = scanner.nextLine().strip();
                                email = Input;
                                mainMenu.create_account(email,firstName,lastName);
                                wrongemail = false;
                            }catch (Exception e){
                                System.out.println("Error, Invalid email.");
                            }
                        }
                        Object reservation = mainMenu.reserve_room(email, firstName, lastName, roomNo, checkInDate, checkOutDate);
                        System.out.println("Reservation Completed");
                        System.out.println(reservation);
                        //keepRunning = false;

                        boolean requestrunning = true;
                        while (requestrunning){
                            System.out.println("Enter y to go back to Main menu");
                            String response = scanner.nextLine().strip().toLowerCase();
                            if(response.equals("y")){
                                requestrunning = false;
                            }
                        }

                    }else if (selection == 2){

                        boolean wrongemail = true;
                        while (wrongemail){
                            try{
                                System.out.println("Please enter your email address or 0 to Exit");
                                String Inputemail = scanner.nextLine().strip();
                                email = Inputemail;
                                if(email.equals("0")){
                                    Menu();
                                }
                                List<Reservations> customersReserve= mainMenu.see_my_reservations(email);
                                System.out.println("\nCustomer's Reservation");
                                for(Reservations reserve:customersReserve){System.out.println("This is reserved: " + reserve);}
                                wrongemail = false;
                            }catch (Exception e){
                                System.out.println("Error, Invalid email.");
                            }


                        }
                        //keepRunning = false;
                        boolean requestrunning = true;
                        while (requestrunning){
                            System.out.println("Enter y to go back to Main menu");
                            String response = scanner.nextLine().strip().toLowerCase();
                            if(response.equals("y")){
                                requestrunning = false;
                            }
                        }


                    }else if(selection ==3){
                        System.out.println("Please enter your first name");
                        String Input = scanner.nextLine().strip().toUpperCase();
                        firstName = Input;
                        System.out.println("Please enter your last name");
                        Input = scanner.nextLine().strip().toUpperCase();
                        lastName = Input;
                        boolean wrongemail = true;
                        while (wrongemail){
                            try{
                                System.out.println("Please enter your email address");
                                Input = scanner.nextLine().strip();
                                email = Input;
                                mainMenu.create_account(email,firstName,lastName);
                                wrongemail = false;
                            }catch (Exception e){
                                System.out.println("Error, Invalid email.");
                            }
                        }
                        //keepRunning = false;

                        boolean requestrunning = true;
                        while (requestrunning){
                            System.out.println("Enter y to go back to Main menu");
                            String response = scanner.nextLine().strip().toLowerCase();
                            if(response.equals("y")){
                                requestrunning = false;
                            }
                        }


                    }else if(selection ==4){
                        keepRunning = false;
                        Admin();

                    }else if(selection == 5){
                        keepRunning = false;
                        startApp();
                    }else {
                        System.out.println("Please enter a number between 1 and 5");
                    }
                }catch (Exception e){
                    System.out.println("\n Error - Invalid Input \n");
                }
            }


        }
    }

    public void Admin(){
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try{
                    System.out.println("Admin");
                    System.out.println("1. See all Customers");
                    System.out.println("2. See all Rooms");
                    System.out.println("3. See all Reservations");
                    System.out.println("4. Add a Room");
                    System.out.println("5. Back to Main Menu");
                    System.out.println("Please enter your selection from 1 to 5");
                    int selection = Integer.parseInt(scanner.nextLine());

                    if(selection == 1){
                        List<Customer> customers = adminMenu.see_all_customers();
                        System.out.println("\nAll Customers");
                        for(Customer customer:customers){System.out.println(customer);}
                        boolean requestrunning = true;
                        while (requestrunning){
                            System.out.println(" Enter y to go back to Admin menu");
                            String response = scanner.nextLine().strip().toLowerCase();
                            if(response.equals("y")){
                                requestrunning = false;
                            }
                        }

                    }else if (selection == 2){
                        List<IRoom>  rooms = adminMenu.see_all_rooms();
                        System.out.println("All Rooms");
                        for(IRoom room:rooms){System.out.println("\n" + room);}

                        boolean requestrunning = true;
                        while (requestrunning){
                            System.out.println(" Enter y to go back to Admin menu");
                            String response = scanner.nextLine().strip().toLowerCase();
                            if(response.equals("y")){
                                requestrunning = false;
                            }
                        }

                    }else if(selection ==3){

                        List<Reservations> reservations = adminMenu.see_all_reservations();
                        System.out.println("\nAll Reservations");
                        for(Reservations reservation:reservations){System.out.println(reservation);}

                        boolean requestrunning = true;
                        while (requestrunning){
                            System.out.println(" Enter y to go back to Admin menu");
                            String response = scanner.nextLine().strip().toLowerCase();
                            if(response.equals("y")){
                                requestrunning = false;
                            }
                        }

                    }else if(selection ==4){
                        boolean running = true;
                        while (running) {
                            boolean roomrunning = true;
                            while (roomrunning) {
                                try {
                                    System.out.println("Enter a room number");
                                    Integer roomInput = Integer.parseInt(scanner.nextLine());
                                    roomNo = roomInput.toString();
                                    roomrunning = false;
                                } catch (Exception e) {
                                    System.out.println("\n Error - Invalid Input \n");
                                }
                            }
                            boolean priceRunning = true;
                            while (priceRunning) {
                                try {
                                    System.out.println("Enter price per night.  example 100.00");
                                    Double roomInput = Double.parseDouble(scanner.nextLine());
                                    roomPrice = roomInput;
                                    priceRunning = false;
                                } catch (Exception e) {
                                    System.out.println("\n Error - Invalid Input - enter price\n");
                                }
                            }
                            boolean roomtyperunning = true;
                            while (roomtyperunning) {
                                System.out.println("Choose Room Type");
                                System.out.println("1. SINGLE BED");
                                System.out.println("2. DOUBLE BED");
                                System.out.println("Please enter your selection between 1 and 2");
                                int roomInput = Integer.parseInt(scanner.nextLine());
                                if (roomInput == 1) {
                                    roomType = RoomType.SINGLE_BED;
                                    roomtyperunning = false;
                                }else if (roomInput == 2) {
                                    roomType = RoomType.DOUBLE_BED;
                                    roomtyperunning = false;
                                }else {
                                    System.out.println("Please enter valid selection between 1 and 2");
                                }
                            }
                            adminMenu.add_room(roomNo, roomPrice, roomType);
                            boolean responseRunning = true;
                            while(responseRunning){
                            System.out.println("Would you like to add another room   y/n");
                            String response = scanner.nextLine().strip().toLowerCase();
                            if(response.equals("y".strip())){
                                responseRunning =false;
                            }else if(response.equals("n".strip())){
                                responseRunning =false;
                                running = false;
                            }else {
                                System.out.println("Please enter either y or n");
                                }
                            }
                        }
                    }else if(selection == 5){
                        keepRunning = false;
                        Menu();
                    }else {
                        System.out.println("Please enter a number between 1 and 5");
                    }
                }catch (Exception e){
                    System.out.println("\n Error - Invalid Input \n");
                }
            }


        }
    }

    public void startApp(){
        boolean keepRunning = true;
        try(Scanner scanner = new Scanner(System.in)){
            while (keepRunning) {
                try {
                    System.out.println("Hotel Reservation Application");
                    System.out.println("1. Go to Main Menu");
                    System.out.println("Please enter 1 to go to Main Menu");
                    int selection = Integer.parseInt(scanner.nextLine());
                    if (selection == 1) {
                        keepRunning = false;
                        Menu();
                    } else {
                        System.out.println("Please enter 1 to go to Main Menu");
                    }
                } catch (Exception e) {
                    System.out.println("\n Error - Invalid Input \n");
                }
            }
        }
    }

    public static void main(String[] args){
        HotelApplication hotelApplication = new HotelApplication();

        hotelApplication.Menu();
    }


}
