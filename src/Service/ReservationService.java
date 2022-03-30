package Service;

import model.*;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.*;
import java.util.stream.Stream;
import model.Room;
import model.IRoom;
public final class ReservationService {

    private static ReservationService reservationService = null;
    private static Map<String,IRoom> roomStorage;
    private static Map<String,Reservations> reserveStorage;


    private ReservationService(){}

    public static ReservationService ReservationService(){
        if(reservationService == null){
            reservationService = new ReservationService();
        }

        return reservationService;
    }

    Boolean checkReserveStorageNull(){
        if(reservationService == null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void addRoom(String roomNo,Double roomPrice, RoomType roomType){
        IRoom room = new Room(roomNo,roomPrice,roomType);
        if(roomStorage == null){
            roomStorage = new HashMap<String,IRoom>();
        }
        if(roomStorage.containsKey(room.getRoomNumber())){
            System.out.println(room + " 'Already Exist' ");
        }else {
            roomStorage.put(room.getRoomNumber(),room);
        }
    }
    public List<IRoom> getAllRooms(){
        if(!(roomStorage == null)){
            List<IRoom> allRooms = new ArrayList<IRoom>(roomStorage.values());
            return allRooms;
        }else {
            System.out.println("No room registered yet");
            return null;
        }

    }

    public IRoom getARoom(String roomNumber){
        if(roomStorage.containsKey(roomNumber)) {
            return roomStorage.get(roomNumber);
        }else {
            System.out.println("Room " + roomNumber + " not available");
            return null;
        }
    }

    public Reservations reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Date presentDate = new Date();
        if(!(checkReserveStorageNull())) {
            try {
                for (Reservations reservation : reserveStorage.values()) {
                    if (presentDate.after(reservation.getCheckOutDate())) {
                        IRoom room1 = reservation.getRoom();
                        room1.setAvailable(true);
                        reserveStorage.remove(reservation.getCustomer().getEmail(), reservation);
                    }
                }
            } catch (Exception e) {
            }
        }
        if(reserveStorage == null){
            reserveStorage = new HashMap<String,Reservations>();
        }
        if(room.isAvailable()){
            Reservations newReserve = new Reservations(customer,room,checkInDate,checkOutDate);
            reserveStorage.put(customer.getEmail(), newReserve);
            room.setAvailable(false);
            return newReserve;
        }else {

           System.out.println("Room requested for is not Available");
           return null;
        }

    }


    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {


        try {
            Set<IRoom> nonAvail = new HashSet<IRoom>();
            Set<IRoom> allrooms = new HashSet<IRoom>(roomStorage.values());
            if (!(reserveStorage == null)) {
                for (Reservations reservation : reserveStorage.values()) {
                    if (reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate)) {
                        try {
                            IRoom room = reservation.getRoom();
                            nonAvail.add(room);
                        }catch (Exception e){

                        }

                    }
                }
            }

            allrooms.removeAll(nonAvail);
            List<IRoom> availableRooms = new ArrayList<IRoom>(allrooms);

            return availableRooms;
        } catch (UnsupportedOperationException e) {
            //e.getLocalizedMessage();
            return null;
        }

    }

    public List<Reservations> getCustomersReservation(Customer customer){
        if((reserveStorage.containsKey(customer.getEmail()))) {
            List<Reservations> CustomersReservation = new ArrayList<Reservations>();
            Reservations custReserve = reserveStorage.get(customer.getEmail());
            CustomersReservation.add(custReserve);
            return CustomersReservation;
        }else {
            System.out.println(customer + " doesn't exist");
            return null;
        }

    }

    public List<Reservations> printAllReservation(){
        try {
            if(!(reserveStorage == null)){
                List<Reservations> reservation = new ArrayList<Reservations>(reserveStorage.values());
                return  reservation;
            }
        }catch (Exception e){
            e.getLocalizedMessage();
        }
        System.out.println("No Reservation found");
        return null;

    }





}
