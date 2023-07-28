package com.company.customerdataservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private CustomerRepository repo;

    private Customer customer;

    @BeforeEach
    public void setup() {
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

    @Test
    public void shouldAddCustomerTest() throws Exception {
        when(repo.save(any(Customer.class))).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customers")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Bob"))
                .andExpect(jsonPath("$.lastName").value("Saget"))
                .andExpect(jsonPath("$.email").value("Bobsaget@gmail.com"))
                .andExpect(jsonPath("$.company").value("Full House"))
                .andExpect(jsonPath("$.phone").value("678-989-9087"))
                .andExpect(jsonPath("$.address1").value("Full House"))
                .andExpect(jsonPath("$.address2").value("Street"))
                .andExpect(jsonPath("$.city").value("San Francisco"))
                .andExpect(jsonPath("$.state").value("California"))
                .andExpect(jsonPath("$.postalCode").value("11111"))
                .andExpect(jsonPath("$.country").value("United States"));
    }

    @Test
    public void shouldUpdateCustomerTest() throws Exception {
        when(repo.save(any(Customer.class))).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customers")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customers/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetCustomerByIdTest() throws Exception {
        when(repo.findById(1)).thenReturn(Optional.of(customer));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bob"))
                .andExpect(jsonPath("$.lastName").value("Saget"))
                .andExpect(jsonPath("$.email").value("Bobsaget@gmail.com"))
                .andExpect(jsonPath("$.company").value("Full House"))
                .andExpect(jsonPath("$.phone").value("678-989-9087"))
                .andExpect(jsonPath("$.address1").value("Full House"))
                .andExpect(jsonPath("$.address2").value("Street"))
                .andExpect(jsonPath("$.city").value("San Francisco"))
                .andExpect(jsonPath("$.state").value("California"))
                .andExpect(jsonPath("$.postalCode").value("11111"))
                .andExpect(jsonPath("$.country").value("United States"));
    }

    @Test
    public void shouldGetCustomerByStateTest() throws Exception {
        when(repo.findByState("California")).thenReturn(Arrays.asList(customer));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customers/state/{state}", "California"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bob"))
                .andExpect(jsonPath("$.lastName").value("Saget"))
                .andExpect(jsonPath("$.email").value("Bobsaget@gmail.com"))
                .andExpect(jsonPath("$.company").value("Full House"))
                .andExpect(jsonPath("$.phone").value("678-989-9087"))
                .andExpect(jsonPath("$.address1").value("Full House"))
                .andExpect(jsonPath("$.address2").value("Street"))
                .andExpect(jsonPath("$.city").value("San Francisco"))
                .andExpect(jsonPath("$.state").value("California"))
                .andExpect(jsonPath("$.postalCode").value("11111"))
                .andExpect(jsonPath("$.country").value("United States"));
    }
}