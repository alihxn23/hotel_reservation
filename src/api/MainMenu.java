package api;

import api.AdminMenu;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void findAndReserveRoom(){
        boolean correctDate = false;
        Date checkInDate = new Date();
        Date checkOutDate = new Date();
        while(!correctDate){
            System.out.println("enter checkIn date mm/dd/yyyy");
            String checkIn = scanner.nextLine();
            System.out.println("enter checkOut date mm/dd/yyyy");
            String checkOut = scanner.nextLine();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            try{
                checkInDate = formatter.parse(checkIn);
                checkOutDate = formatter.parse(checkOut);
                if(checkOutDate.before(checkInDate)){
                    throw new IllegalArgumentException("invalid. checkout date can not be before checkin date");
                }
                correctDate = true;
            } catch(ParseException e){
                System.out.println("invalid date");
            } catch(IllegalArgumentException e){
                System.out.println(e.getLocalizedMessage());
            }

        }
        Collection<IRoom> availableRooms = HotelResource.findARoom(checkInDate, checkOutDate);
        if(availableRooms.isEmpty()){
            System.out.println("going to add 7 days to checkin and checkout dates");
            Calendar cal = Calendar.getInstance();

            cal.setTime(checkInDate);
            cal.add(Calendar.DATE, 7);
            checkInDate = (Date) cal.getTime();
            System.out.println(checkInDate);

            cal.setTime(checkOutDate);
            cal.add(Calendar.DATE, 7);
            checkOutDate = (Date) cal.getTime();
            System.out.println(checkOutDate);
            availableRooms = HotelResource.findARoom(checkInDate, checkOutDate);
            if(availableRooms.isEmpty()){
                System.out.println("no rooms found");
                return;
            }
        }
        for (IRoom r : availableRooms) {
                System.out.println(r);
            }


        String option = "";
        while(!(option.equals("y") || option.equals("n"))){
            System.out.println("would you like to book a room? (y/n)");
            option = scanner.nextLine();
        }
        if(option.equals("y")){
            String acc = "";
            while(!(acc.equals("y") || acc.equals("n"))){
                System.out.println("do you have an account with us? (y/n)");
                acc = scanner.nextLine();
            }
            if(acc.equals("n")){
                System.out.println("lets create an account");
                createAccount();
            }
            boolean correctEmail = false;
            Customer c;
            String email = "";
            while(!correctEmail){
                System.out.println("enter email: ");
                email = scanner.nextLine();
                try{
                    c = HotelResource.getCustomer(email);
                } catch(IllegalArgumentException e){
                    System.out.println(e.getLocalizedMessage());
                }
                correctEmail = true;
            }
            boolean correctRoom = false;
            String roomNumber = "";
            while(!correctRoom){
                System.out.println("what room would you like to book");
                roomNumber = scanner.nextLine();
                IRoom room = HotelResource.getRoom(roomNumber);
                if(room != null && availableRooms.contains(room)){
                    correctRoom = true;
                } else {
                    System.out.println("invalid room number");
                }
            }
            System.out.println(HotelResource.bookARoom(email, HotelResource.getRoom(roomNumber), checkInDate, checkOutDate));
        } else {
            return;
        }
    }

    public static void seeReservations(){
        boolean correctEmail = false;
        while(!correctEmail){
            System.out.println("enter your email: ");
            String email = scanner.nextLine();
            try{
                Collection<Reservation> reservations = HotelResource.getCustomersReservations(email);
                correctEmail = true;
                if(reservations.isEmpty()){
                    System.out.println("no reservations found");
                } else {
                    for(Reservation r : reservations){
                        System.out.println(r);
                    }
                }
            } catch(IllegalArgumentException e){
                System.out.println("invalid email");
            }
        }
    }

    public static void createAccount(){
        boolean correctEmail = false;
        while(!correctEmail){
            System.out.println("enter your email: ");
            String email = scanner.nextLine();
            System.out.println("First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("Last Name: ");
            String lastName = scanner.nextLine();
            try{
                HotelResource.createACustomer(email, firstName, lastName);
                correctEmail = true;
            } catch(IllegalArgumentException e){
                System.out.println("invalid email entered");
            }
        }
    }

    public static void admin(){
        AdminMenu.init();
    }

    public void exit(){
        return;
    }

    public static void init(){
        String option = "";
        while(!option.equals("5")){
            System.out.println(
                    "--------------- MAIN MENU -----------------\n" +
                    "1. find and reserve a room\n" +
                    "2. see my reservations\n" +
                    "3. create an account\n" +
                    "4. admin\n" +
                    "5. exit\n"
            );
            option = scanner.nextLine();
            switch(option){
                case "1" : {findAndReserveRoom(); break;}
                case "2" : {seeReservations(); break;}
                case "3" : {createAccount(); break;}
                case "4" : {admin(); break;}
                default : break;
            }
        }
        System.out.println("good bye");
    }
}
