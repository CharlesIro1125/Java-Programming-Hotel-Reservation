package model;

import java.util.Date;

public class Reservations implements Comparable<Reservations>{
    private Customer customer;
    private  IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservations(Customer customer,IRoom room,Date checkInDate,Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

    }
    public IRoom getRoom(){
        return this.room;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public Date getCheckInDate(){
        return this.checkInDate;
    }
    public Date getCheckOutDate(){
        return this.checkOutDate;
    }

    public String toString(){
        return this.customer + " | " + this.room + " | " + this.checkInDate + " | " + this.checkOutDate;
    }

    @Override
    public int compareTo(Reservations o) {
        return this.checkInDate.compareTo(o.checkInDate);
    }
}
