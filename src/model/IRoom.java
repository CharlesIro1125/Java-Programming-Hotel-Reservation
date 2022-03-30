package model;

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();

    void setRoomPrice(Double price);

    public RoomType getRoomType();

    void setRoomType(RoomType enumeration);

    void setAvailable(boolean val);

    public boolean isAvailable();
}
