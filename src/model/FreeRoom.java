package model;

public class FreeRoom extends Room{
    public FreeRoom(String n, RoomType rt){
        super(n, 0.0, rt);
    }

    @Override
    public String toString() {
        return "this is a free room";
    }
}
