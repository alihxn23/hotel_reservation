package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static ReservationService reservationService = ReservationService.getInstance();
    private static CustomerService customerService = CustomerService.getInstance();

    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    };

    public static void addRoom(List<IRoom> rooms){
        for(IRoom r : rooms){
            reservationService.addRoom(r);
        }
    };
    public static Collection<IRoom> getAllRooms(){
        return (Collection<IRoom>) (Object) reservationService.getRooms();
    };
    public static Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    };
    public static void displayAllReservations(){
        reservationService.printAllReservation();
    };
}
