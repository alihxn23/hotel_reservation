package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer c, IRoom r, Date ci, Date co){
        customer = c;
        room = r;
        checkInDate = ci;
        checkOutDate = co;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public IRoom getRoom() {
        return room;
    }

    void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    private void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    final void setRoom(IRoom room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Room Number: " + this.room.getRoomNumber() + " " + this.customer.toString() + " checked in on " + this.checkInDate + " and is checking out on " + this.checkOutDate;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
