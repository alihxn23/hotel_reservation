package api;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
//    1. see all customers
//    2. see all rooms
//    3. see all reservations
//    4. add a room
//    5. add test data
//    6. back to main menu
    static Scanner scanner = new Scanner(System.in);

    public static void seeAllCustomers(){
        for(Customer c : AdminResource.getAllCustomers()){
            System.out.println(c);
        }
    };

    public static void seeAllRooms(){
        for(IRoom r : AdminResource.getAllRooms()){
            System.out.println(r);
        }
    };

    public static void seeAllReservations(){
        AdminResource.displayAllReservations();
    };

    public static void addARoom(){

        String option;
        String roomNumber = "1";
        String roomType = "3";
        double roomPrice = 0;
        boolean correctRoomPrice = false;
        boolean correctRoomNumber = false;
        ArrayList<IRoom> rooms = new ArrayList<>();
        do{
            while(!correctRoomNumber){
                System.out.println("enter room number: ");
                roomNumber = scanner.nextLine();
                if(HotelResource.getRoom(roomNumber) != null){
                    System.out.println("this room already exists. try again");
                } else {
                    correctRoomNumber = true;
                }
            }


            while(!correctRoomPrice){
                try{
                    System.out.println("enter room price: ");
                    roomPrice = scanner.nextDouble();
                    correctRoomPrice = true;
                } catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("invalid room price! try again");
                    correctRoomPrice = false;
                }
            }

            scanner.nextLine();

            while(!(roomType.equals("1") || roomType.equals("2"))){
                System.out.println("enter room type: 1 for single bed, 2 for double bed");
                roomType = scanner.nextLine();
            }

            if(roomType.equals("1")){
                rooms.add(new Room(roomNumber, roomPrice, RoomType.SINGLE));

            } else {
                rooms.add(new Room(roomNumber, roomPrice, RoomType.DOUBLE));

            }

            AdminResource.addRoom(rooms);

            System.out.println("do you want to add another room? (y/n)");
            option = scanner.nextLine();

            if(option.equals("n")){
                break;
            }

            roomNumber = "1";
            roomType = "3";
            roomPrice = 0;
            correctRoomPrice = false;
            correctRoomNumber = false;
            rooms.clear();
        }while(option.equals("y"));
    };

    public static void addTestData(){
        HotelResource.createACustomer("a@b.com", "hello", "world");
        HotelResource.createACustomer("c@d.com", "mike", "race");
        ArrayList<IRoom> rooms = new ArrayList<>();
        rooms.add(new Room("100", 350, RoomType.SINGLE));
        rooms.add(new Room("200", 250, RoomType.SINGLE));
        rooms.add(new Room("300", 550, RoomType.DOUBLE));
        rooms.add(new Room("400", 750, RoomType.DOUBLE));
        AdminResource.addRoom(rooms);
        HotelResource.bookARoom("a@b.com", HotelResource.getRoom("100"), new Date(122,02,3), new Date(122, 02, 20));
        HotelResource.bookARoom("c@d.com", HotelResource.getRoom("100"), new Date(122,03,13), new Date(122, 03, 28));
        return;
    }

    public static void back(){
        return;
    };

    public static void init(){
        String option = "";
        while(!option.equals("6")){
            System.out.println(
                "--------------- ADMIN MENU -----------------\n" +
                "1. see all customers\n" +
                "2. see all rooms\n" +
                "3. see all reservations\n" +
                "4. add a room\n" +
                "5. add test data\n" +
                "6. back to main menu"
            );
            option = scanner.nextLine();
            switch(option){
                case "1" : {seeAllCustomers(); break;}
                case "2" : {seeAllRooms(); break;}
                case "3" : {seeAllReservations(); break;}
                case "4" : {addARoom(); break;}
                case "5" : {addTestData(); break;}
                case "6" : {back(); break;}
                default : break;
            }
        }
    }
}
