package api;

import Service.CustomerService;
import Service.ReservationService;
import model.Customer;
import model.IRoom;
import model.Reservations;

import java.util.Date;
import java.util.List;

public final class HotelResource {
    private static HotelResource hotelResource = null;
    private CustomerService customerService = CustomerService.CustomerService();
    private ReservationService reservationService = ReservationService.ReservationService();


    private HotelResource(){}

    public static HotelResource HotelResource(){
        if(hotelResource == null){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email,String firstname,String lastname){
        customerService.addCustomer(email,firstname,lastname);
    }


    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }


    public Reservations bookARoom(String email,String firstname,String lastname,String roomNo, Date checkInDate,Date checkOutDate){
        Customer customer = getCustomer(email);
        IRoom room = getRoom(roomNo);
        return reservationService.reserveARoom(customer,room,checkInDate,checkOutDate);
    }


    public List<Reservations> getCustomersReservations(String customerEmail){
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }


    public List<IRoom> findARoom(Date checkInDate,Date checkOutDate){
        return reservationService.findRooms(checkInDate,checkOutDate);
    }

}
