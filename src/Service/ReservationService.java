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
    private static HashMap<String,IRoom> roomStorage;
    private static HashMap<String,Reservations> reserveStorage;


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
    String reserveKey(String email,String roomNumber,Date checkIn,Date checkOut){
        return String.format("%s#%s#%s#%s", email,roomNumber,checkIn,checkOut);
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

        if(reserveStorage == null){
            reserveStorage = new HashMap<String,Reservations>();
        }
        Date presentDate = new Date();
        Instant instant = Instant.now();
        Calendar calendar3 = Calendar.getInstance();
        calendar3.clear();

        calendar3.setTime(presentDate);
        presentDate = calendar3.getTime();
        if(!(checkReserveStorageNull())) {
            try {
                for (Reservations reservation : reserveStorage.values()) {
                    if ((calendar3.after(reservation.getCheckOutDate()))) {
                        System.out.println("Avoided area  "+"  presentTime  " + presentDate );
                        System.out.println("Avoided area  "+"  ChectOutDate  " + reservation.getCheckOutDate() );
                        IRoom room1 = reservation.getRoom();
                        room1.setAvailable(Boolean.TRUE);

                        reserveStorage.remove(reserveKey(reservation.getCustomer().getEmail(),
                        reservation.getRoom().getRoomNumber(),
                        reservation.getCheckInDate(),reservation.getCheckOutDate()), reservation);
                    }
                }
            } catch (Exception e) {
            }
        }


        room.setAvailable(false);
        Reservations newReserve = new Reservations(customer,room,checkInDate,checkOutDate);
        newReserve.getRoom().setAvailable(false);
        reserveStorage.put(reserveKey(newReserve.getCustomer().getEmail(),
                newReserve.getRoom().getRoomNumber(),newReserve.getCheckInDate(),
                newReserve.getCheckOutDate()), newReserve);

        return newReserve;

    }


    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        System.out.println("CheckInDate : " + checkInDate + "   CheckOutDate : " + checkOutDate);


        try {
            HashSet<IRoom> nonAvail = new HashSet<IRoom>();
            HashSet<IRoom> allrooms = new HashSet<IRoom>(roomStorage.values());
            if (!(reserveStorage == null)) {
                for (Reservations reservation : reserveStorage.values()) {
                    if (checkInDate.after(reservation.getCheckOutDate()) || checkOutDate.before(reservation.getCheckInDate()) ){
                        //checkInDate.after(reservation.getCheckOutDate()) || checkOutDate.before(reservation.getCheckInDate())
                        reservation.getRoom().setAvailable(true);
                    }else {
                        IRoom room = reservation.getRoom();
                        nonAvail.add(room);
                    }
                }
            }
            try {
                allrooms.removeAll(nonAvail);
            }catch (NullPointerException e){

            }

            List<IRoom> availableRooms = new ArrayList<IRoom>(allrooms);
            return availableRooms;
        } catch (UnsupportedOperationException e) {
            e.getLocalizedMessage();
            return null;
        }

    }

    public List<Reservations> getCustomersReservation(Customer customer){
        List<Reservations> CustomersReservation = new ArrayList<Reservations>();
        for(Reservations reservations : reserveStorage.values()) {
            if (reservations.getCustomer().getEmail().equals(customer.getEmail())) {
                reservations.getRoom().setAvailable(false);
                CustomersReservation.add(reservations);

            }
        }
        return CustomersReservation;
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
