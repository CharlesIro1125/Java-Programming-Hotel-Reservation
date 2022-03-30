package Menu;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Reservations;
import model.RoomType;

import java.util.List;

public class AdminMenu {
    private AdminResource adminResource = AdminResource.AdminResource();
    public AdminMenu(){}

    public List<Customer> see_all_customers(){
        return adminResource.getAllCustomers();

    }

    public List<IRoom> see_all_rooms(){
        return adminResource.getAllRooms();

    }

    public List<Reservations> see_all_reservations(){
        return adminResource.displayAllReservations();

    }
    public void add_room(String roomNo,Double roomPrice, RoomType roomType){
        adminResource.addRooms(roomNo,roomPrice,roomType);
    }

}
