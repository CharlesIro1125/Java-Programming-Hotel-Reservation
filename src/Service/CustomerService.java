package Service;

import model.Customer;

import java.util.*;

public final class CustomerService {
    private static CustomerService customerService = null;
    private static Map<String,Customer> customerMap;

    private CustomerService() {

    }

    public static CustomerService CustomerService(){
        if(customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email,String firstName,String lastName){
        try {
            if (customerMap == null) {
                customerMap = new HashMap<String, Customer>();

            }
            Customer customer = new Customer(firstName, lastName, email);
            if (customerMap.containsValue(customer)) {
                System.out.println(" Welcome  " + customer);
            } else {
                customerMap.put(email, customer);
            }
        }catch (Exception e){
            System.out.println("Error, Invalid email.Please provide valid email");
            throw new IllegalArgumentException("Error, Invalid email.");
        }
    }
    public Customer getCustomer(String customerEmail){
        try{
            if(customerMap.containsKey(customerEmail))
                return customerMap.get(customerEmail);
        }catch (Exception e){
            e.getLocalizedMessage();
        }
        System.out.println(customerEmail + "  Email doesn't exist");
        return null;
    }
    public List<Customer> getAllCustomers(){
        try {
            if(!(customerMap == null)){
                List<Customer> customers = new ArrayList<Customer>(customerMap.values());
                return  customers;
            }
        }catch (Exception e){
            e.getLocalizedMessage();
        }
        System.out.println("No customer registered");
        return null;

    }
}
