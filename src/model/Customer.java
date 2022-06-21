package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String f, String l, String e){
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(e).matches()){
            throw new IllegalArgumentException("invalid email");
        }
        firstName = f;
        lastName = l;
        email = e;
    }

    String getLastName() {
        return lastName;
    }

    String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " Email: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
