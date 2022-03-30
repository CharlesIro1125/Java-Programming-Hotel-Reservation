package Menu;

import Service.CustomerService;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservations;

import java.util.Date;
import java.util.List;

public class MainMenu {
    private HotelResource hotelResource = HotelResource.HotelResource();
    public MainMenu(){}

    public List<IRoom> find_rooms(Date checkInDate, Date checkOutDate){
        return hotelResource.findARoom(checkInDate,checkOutDate);

    }
    public Reservations reserve_room(String email,String firstname,String lastname,String roomNo,
                               Date checkInDate, Date checkOutDate){
        Reservations reservation = hotelResource.bookARoom(email,firstname,lastname,
                roomNo, checkInDate, checkOutDate);
        return reservation;

    }

    public List<Reservations> see_my_reservations(String email){
        return  hotelResource.getCustomersReservations(email);

    }

    public void create_account(String email,String firstname,String lastname){
        hotelResource.createACustomer(email,firstname,lastname);

    }

}
