package model;

public class Room implements IRoom{
    private String roomNumber;
    private double price;
    private RoomType roomType;

    public Room(String rn, double p, RoomType rt){
        roomNumber = rn;
        price = p;
        roomType = rt;
    }


    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public double getRoomPrice() {
        return price;
    }

    @Override
    public boolean isFree() {
        if(price == 0.0) return true;
        else return false;
    }

    void setPrice(double price) {
        this.price = price;
    }

    private void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return " Room number: " + roomNumber + " of type: " + roomType.toString() + " costs: " + price;
    }
}
