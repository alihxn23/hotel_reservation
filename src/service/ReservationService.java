package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class ReservationService {
    private static ReservationService sReservationService;
    static ArrayList<Reservation> reservations;
    static ArrayList<Room> rooms;

    private ReservationService(){
        reservations = new ArrayList<>();
        rooms=new ArrayList<>();
    }

    public synchronized static ReservationService getInstance(){
        if(sReservationService == null){
            sReservationService = new ReservationService();
        }
        return sReservationService;
    }


    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static void addRoom(IRoom room){
        rooms.add((Room) room);
    };

    public static IRoom getARoom(String roomId){
        for(Room r : rooms){
            if(r.getRoomNumber().equals(roomId)){
                return r;
            }
        }
        return null;
    };

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation n = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(n);
        return n;
    };

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
//        System.out.println();
        Collection<IRoom> available = roomFinder(checkInDate, checkOutDate);
        return available;
    };

    private static Collection<IRoom> roomFinder(Date checkInDate, Date checkOutDate){
        ArrayList<IRoom> available = new ArrayList<>();
        for(Room r : rooms){
            available.add(r);
            for(Reservation re : reservations){
                if(!re.getRoom().equals(r)){
                    continue;
                }
                if (checker(checkInDate, re.getCheckInDate(), checkOutDate, re.getCheckOutDate()))
                {
//                    System.out.println("going to remove room");
                    available.remove(r);
                }
            }
        }
        return available;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        ArrayList<Reservation> customerReservations = new ArrayList<>();
        for(Reservation r : reservations){
            if(r.getCustomer().equals(customer)){
                customerReservations.add(r);
            }
        }
        return customerReservations;
    };

    public static void printAllReservation(){
        for(Reservation r : reservations){
            System.out.println(r);
        }
    };

    static boolean checker(Date checkInDate, Date regetCheckInDate, Date checkOutDate, Date regetCheckOutDate){
        return
        (
            (
                (checkInDate.before(regetCheckInDate) || checkInDate.equals(regetCheckInDate)) &&
                (checkOutDate.after(regetCheckOutDate) || checkOutDate.equals(regetCheckOutDate))
            ) ||
            (
                (checkInDate.before(regetCheckInDate) || checkInDate.equals(regetCheckInDate))&&
                (checkOutDate.after(regetCheckInDate) || checkOutDate.equals(regetCheckInDate))
            ) ||
            (
                (checkInDate.before(regetCheckOutDate) || checkInDate.equals(regetCheckOutDate)) &&
                (checkOutDate.after(regetCheckOutDate) || checkOutDate.equals(regetCheckOutDate))
            )
        );
    };

}
