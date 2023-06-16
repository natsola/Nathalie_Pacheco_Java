package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[]{"1", "Wayne Enterprises", "10000", "12-01-2021"},
            new String[]{"2", "Daily Planet", "-7500", "01-10-2022"},
            new String[]{"1", "Wayne Enterprises", "18000", "12-22-2021"},
            new String[]{"3", "Ace Chemical", "-48000", "01-10-2022"},
            new String[]{"3", "Ace Chemical", "-95000", "12-15-2021"},
            new String[]{"1", "Wayne Enterprises", "175000", "01-01-2022"},
            new String[]{"1", "Wayne Enterprises", "-35000", "12-09-2021"},
            new String[]{"1", "Wayne Enterprises", "-64000", "01-17-2022"},
            new String[]{"3", "Ace Chemical", "70000", "12-29-2022"},
            new String[]{"2", "Daily Planet", "56000", "12-13-2022"},
            new String[]{"2", "Daily Planet", "-33000", "01-07-2022"},
            new String[]{"1", "Wayne Enterprises", "10000", "12-01-2021"},
            new String[]{"2", "Daily Planet", "33000", "01-17-2022"},
            new String[]{"3", "Ace Chemical", "140000", "01-25-2022"},
            new String[]{"2", "Daily Planet", "5000", "12-12-2022"},
            new String[]{"3", "Ace Chemical", "-82000", "01-03-2022"},
            new String[]{"1", "Wayne Enterprises", "10000", "12-01-2021"});

    public static void main(String[] args) {
          List<Customer> customers = new ArrayList<>();

        // Iterate through customerData and convert it into a list of Customer objects
        for (String[] customerInfo : customerData) {
            int id = Integer.parseInt(customerInfo[0]);
            String name = customerInfo[1];
            int charge = Integer.parseInt(customerInfo[2]);
            String chargeDate = customerInfo[3];

            // Check if the customer already exists in the list
            Customer customer = findCustomerById(customers, id);

            if (customer == null) {
                customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customers.add(customer);
            }

            AccountRecord accountRecord = new AccountRecord();
            accountRecord.setCharge(charge);
            accountRecord.setChargeDate(chargeDate);

            customer.getCharges().add(accountRecord);
        }

        // Print the details of each customer
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

        // Display accounts with a positive balance
        System.out.println("Accounts with a positive balance:");
        for (Customer customer : customers) {
            if (customer.getBalance() > 0) {
                System.out.println(customer.toString());
            }
        }

        // Display accounts with a negative balance
        System.out.println("Accounts with a negative balance:");
        for (Customer customer : customers) {
            if (customer.getBalance() < 0) {
                System.out.println(customer.toString());
            }
        }
    }
    private static Customer findCustomerById(List < Customer > customers,int id){
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
}
