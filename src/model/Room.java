package model;

public class Room  implements IRoom{
    private String roomNumber = "";
    private Double roomPrice = 0.0;
    private RoomType roomType;
    private boolean available;

    public Room(String RoomNumber, Double RoomPrice, RoomType roomType){
        this.roomNumber = RoomNumber;
        this.roomPrice = RoomPrice;
        this.roomType = roomType;
        this.available = true;

    }



    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.roomPrice;
    }
    @Override
    public void setRoomPrice(Double price) {
        this.roomPrice = price;
    }

    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }
    @Override
    public void setRoomType(RoomType enumeration) {
        this.roomType = enumeration;
    }
    @Override
    public void setAvailable(boolean val) {
        this.available = val;
    }


    @Override
    public boolean isAvailable() {
        return this.available;
    }
    @Override
    public boolean equals(Object other){
        if(this == other ){
            return true;
        }
        if(!(other instanceof IRoom)){
            return false;
        }
        Room obj = (Room) other;
        return this.roomNumber == obj.roomNumber;

    }
    @Override
    public String toString(){
        return "Room: " + this.roomNumber + "  Price per night: " + this.roomPrice + "  RoomType: "
                + this.roomType + "  Available: " + this.available;
    }
}
