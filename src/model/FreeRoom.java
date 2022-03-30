package model;

public class FreeRoom extends Room{
    private String roomNumber = "";
    private Double roomPrice = 0.0;
    private RoomType roomType;
    private boolean available;


    public FreeRoom(String RoomNumber, Double RoomPrice, RoomType roomType) {
        super(RoomNumber, 0.0, roomType);
        roomNumber = RoomNumber;
        roomPrice = RoomPrice;
        roomType = roomType;
        available = true;
    }

    @Override
    public String toString(){
        return "FreeRoom: " + this.roomNumber + "  Price per night: " + this.roomPrice + "  RoomType: "
                + this.roomType + "  Available: " + this.available;
    }




}
