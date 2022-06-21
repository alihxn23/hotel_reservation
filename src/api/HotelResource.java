package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static ReservationService reservationService = ReservationService.getInstance();
    private static CustomerService customerService = CustomerService.getInstance();

    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    };
    public static void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    };
    public static IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    };
    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer c = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(c, room, checkInDate, checkOutDate);
    };
    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer c = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(c);
    };
    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    };
}
