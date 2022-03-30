package api;

import Service.CustomerService;
import Service.ReservationService;
import model.Customer;
import model.IRoom;
import model.Reservations;
import model.RoomType;

import java.util.List;

public final class AdminResource {
    private ReservationService reservationService = ReservationService.ReservationService();
    private CustomerService customerService = CustomerService.CustomerService();
    private static AdminResource adminResource = null;


    private AdminResource(){}


    public static AdminResource AdminResource(){
        if(adminResource == null){
            adminResource = new AdminResource();
        }
        return adminResource;
    }


    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRooms(String roomNo,Double roomPrice, RoomType roomType){
        reservationService.addRoom(roomNo,roomPrice,roomType);

    }

    public List<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public List<Reservations> displayAllReservations(){
        return reservationService.printAllReservation();
    }
}
