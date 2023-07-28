package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTests {
    @Autowired
    CustomerRepository repo;

    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        repo.deleteAll();

        //create customer & assign values
        customer = new Customer();

        customer.setFirstName("Bob");
        customer.setLastName("Saget");

        customer.setEmail("Bobsaget@gmail.com");
        customer.setCompany("Full House");
        customer.setPhone("678-989-9087");

        customer.setAddress1("Full House Street");
        customer.setAddress2("Street");

        customer.setCity("San Francisco");
        customer.setState("California");
        customer.setPostalCode("11111");
        customer.setCountry("United States");
    }

    //Add a customer
    @Test
    public void shouldAddCustomer() {
        //Act
        customer = repo.save(customer);

        //Assert
        Optional<Customer> customer1 = repo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    //Update customer
    @Test
    public void shouldUpdateCustomer() {
        //Arrange
        repo.save(customer);

        //Act
        customer.setFirstName("UPDATED");

        repo.save(customer);

        //Assert
        Optional<Customer> customer1 = repo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    //Delete customer
    @Test
    public void shouldDeleteCustomer() {
        //Arrange
        repo.save(customer);

        //Act
        repo.deleteById(customer.getId());

        //Assert
        Optional<Customer> customer1 = repo.findById(customer.getId());

        assertFalse(customer1.isPresent());
    }

    // Get customer by ID
    @Test
    public void shouldGetCustomerById() {
        //Arrange
        customer = repo.save(customer);

        //Act
        Customer customer1 = repo.findById(customer.getId()).orElse(null);

        //Assert
        assertEquals(customer, customer1);
    }

    //Get customer by state
    @Test
    public void shouldGetCustomerByState() {
        //Arrange
        repo.save(customer);


        Customer customer2 = new Customer();

        customer2.setFirstName("George");
        customer2.setLastName("Lopez");
        customer2.setState("California");
        repo.save(customer2);

        //Act
        List<Customer> customers = repo.findByState("California");

        //Assert
        assertTrue(customers.contains(customer));
        assertTrue(!customers.contains(customer2));
    }
}
