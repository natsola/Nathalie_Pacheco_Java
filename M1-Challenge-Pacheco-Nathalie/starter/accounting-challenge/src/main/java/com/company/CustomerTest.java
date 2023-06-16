package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerTest {

    @Test
    public void testGetBalance() {
        Customer customer = new Customer();
        AccountRecord account1 = new AccountRecord();
        account1.setCharge(100);
        customer.getCharges().add(account1);

        AccountRecord account2 = new AccountRecord();
        account2.setCharge(-50);
        customer.getCharges().add(account2);

        AccountRecord account3 = new AccountRecord();
        account3.setCharge(200);
        customer.getCharges().add(account3);

        assertEquals(250, customer.getBalance());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Wayne Enterprises");

        String expected = "Customer ID: 1\nCustomer Name: Wayne Enterprises\nCustomer Balance: 0";
        assertEquals(expected, customer.toString());
    }
}
