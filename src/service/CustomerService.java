package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public class CustomerService {
    private static CustomerService sCustomerService;
    static ArrayList<Customer> customers;

    private CustomerService(){
        customers = new ArrayList<>();
    }

    public synchronized static CustomerService getInstance(){
        if(sCustomerService == null){
            sCustomerService = new CustomerService();
        }
        return sCustomerService;
    }

    public static void addCustomer(String email, String firstName, String lastName){
        for(Customer c : customers){
            if(c.getEmail().equals(email)){
                System.out.println("email is already registered in the database");
                return;
            }
        }
        customers.add(new Customer(firstName, lastName, email));
    };
    public static Customer getCustomer(String customerEmail){
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(customerEmail).matches()){
            throw new IllegalArgumentException("invalid email");
        }
        for(Customer c : customers){
            if(c.getEmail().equals(customerEmail)){
                return c;
            }
        }
        System.out.println(" No customer in the list with the given email");
        return null;
    }
    public static Collection<Customer> getAllCustomers(){
        return customers;
    };
}
