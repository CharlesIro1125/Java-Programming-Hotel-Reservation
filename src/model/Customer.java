package model;

import java.util.regex.Pattern;

public class Customer implements Comparable<Customer> {
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String emailRegex = "^(.+)@(.+).com$";
    private final Pattern pattern = Pattern.compile(emailRegex);


    public Customer(String firstName,String lastName, String email){
        if(!Pattern.matches(emailRegex,email)){
            throw new IllegalArgumentException("Error, Invalid email.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }
    public String getEmail(){
        return this.email;
    }

    @Override
    public int compareTo(Customer o) {
        return this.firstName.compareTo(o.firstName);
    }
    @Override
    public boolean equals(Object other){
        if(this == other ){
            return true;
        }
        if(!(other instanceof Customer)){
            return false;
        }
        Customer obj = (Customer) other;
        return this.firstName == obj.firstName && this.lastName == obj.lastName &&
                this.email == obj.email;

    }
    @Override
    public String toString(){
        return this.firstName + " " + this.lastName + "  email: " + this.email;
    }
}
