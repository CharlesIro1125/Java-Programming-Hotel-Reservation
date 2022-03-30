package Service;

import model.Customer;
import model.IRoom;
import model.Reservations;
import model.RoomType;

import java.time.Instant;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class Tester {
    //Date static checkInDate;
    //Date static checkOutDate;

    public static void main(String[] args) throws Exception{
        CustomerService customerService = CustomerService.CustomerService();
        ReservationService reservationService = ReservationService.ReservationService();
        //Tester tester = new Tester();
        customerService.addCustomer("iro@gmail.com","Charles","Iro");
        customerService.addCustomer("charles@gmail.com","Charles","Iro");
        customerService.addCustomer("mark@gmail.com","mark","Iro");

        Customer newcustomer = customerService.getCustomer("iro@gmail.com");
        Customer newcustomer1 = customerService.getCustomer("charles@gmail.com");
        Customer newcustomer2 = customerService.getCustomer("mark@gmail.com");
        //System.out.println("get customer " + newcustomer);
        List<Customer> Allcustomers = customerService.getAllCustomers();
        //System.out.println("get all customers "+Allcustomers);

        String checkInInput = "05/07/2022";
        Calendar calendar1 = Calendar.getInstance();
        int year = Integer.parseInt(checkInInput.substring(6, 9));
        int month = Integer.parseInt(checkInInput.substring(0, 2));
        int day = Integer.parseInt(checkInInput.substring(3, 5));
        calendar1.clear();
        calendar1.set(year, month - 1, day);
        Date checkInDate = calendar1.getTime();
        //System.out.println("Enter check-out Date mm/dd/yyyy  example: 02/03/2022");
        String checkOutInput = "05/14/2022";
        Calendar calendar2 = Calendar.getInstance();
        year = Integer.parseInt(checkOutInput.substring(6, 9));
        month = Integer.parseInt(checkOutInput.substring(0, 2));
        day = Integer.parseInt(checkOutInput.substring(3, 5));
        calendar2.clear();
        calendar2.set(year, month -1 , day);
        Date checkOutDate = calendar2.getTime();

        //System.out.println("checkin date "+ checkInInput + " " + "checkout date " + checkOutInput);

        String roomNo = "120";
        Double roomPrice = 112.00;
        RoomType roomType = RoomType.DOUBLE_BED;

        reservationService.addRoom(roomNo,roomPrice,roomType);
        reservationService.addRoom("122",80.4,RoomType.SINGLE_BED);
        reservationService.addRoom("121",90.4,RoomType.SINGLE_BED);
        reservationService.addRoom("124",60.4,RoomType.SINGLE_BED);

        IRoom room1 = reservationService.getARoom(roomNo);
        IRoom room3 = reservationService.getARoom("121");
        //System.out.println("getting a room  " + room1);
        IRoom room2 = reservationService.getARoom("122");
        //System.out.println("getting a room  " + room2);


        List<IRoom>  rooms = reservationService.getAllRooms();
        //System.out.println("getting all rooms  " + rooms);
        //for(IRoom room:rooms){System.out.println("\n" + room);}
        Reservations reserved3 = reservationService.reserveARoom(newcustomer,room3,checkInDate,checkOutDate);
        Reservations reserved2 = reservationService.reserveARoom(newcustomer1,room2,checkInDate,checkOutDate);
        Reservations reserved1 = reservationService.reserveARoom(newcustomer2,room1,checkInDate,checkOutDate);
        //for(IRoom room:reserved1){System.out.println("\n" + room);}
        //System.out.println("\n" + reserved3);
        //System.out.println("\n" + reserved2);
        //System.out.println("\n" + reserved1);


        List<IRoom>  findRooms = reservationService.findRooms(checkInDate,checkOutDate);
        //System.out.println("\n Available rooms");
        //for(IRoom room:findRooms){System.out.println(room);}





        List<Reservations> customersReserve = reservationService.getCustomersReservation(newcustomer);
        //System.out.println("\nCustomers Reservation");
        //for(Reservations reserve:customersReserve){System.out.println("this is reserved " + reserve);}


        //System.out.println("\nprintAllReservation");
        reservationService.printAllReservation();

        List<Customer> customers = customerService.getAllCustomers();
        //System.out.println("\nSee all Customers");
        //for(Customer customer:customers){System.out.println(customer);}

        Calendar calend1 = Calendar.getInstance();
        Calendar calend2 = Calendar.getInstance();
        calend1.setTime(checkInDate);
        calend2.setTime(checkOutDate);
        System.out.println("\nSee the newCheckInDate" + checkInDate);
        System.out.println("\nSee the newCheckInDate" + checkOutDate);
        Instant checkIn = calend1.toInstant();
        System.out.println("\nSee the instant : " + checkIn);
        Instant checkOut = calend2.toInstant();
        System.out.println("\nSee the instant : " + checkOut);
        checkIn.plus(Period.ofDays(7));
        checkOut.plus(Period.ofDays(7));
        Date newCheckInDate = Date.from(checkIn);
        Date newCheckOutDate = Date.from(checkOut);
        System.out.println("\nSee the newCheckInDate" + newCheckOutDate);
        System.out.println("\nSee the newCheckInDate" + newCheckInDate);

        findRooms = reservationService.findRooms(checkInDate,checkOutDate);
        System.out.println("\nfind rooms" + findRooms);

    }
}
