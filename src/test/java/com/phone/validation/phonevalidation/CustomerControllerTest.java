package com.phone.validation.phonevalidation;

import com.phone.validation.phonevalidation.model.Customer;
import com.phone.validation.phonevalidation.model.State;
import com.phone.validation.phonevalidation.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().id(1).name("MICHAEL MICHAEL").phone("(237) 677046616").build());
        customers.add(Customer.builder().id(2).name("Yosaf Karrouch").phone("(212) 698054317").build());

        doReturn(customers).when(customerRepository).findAll();
        Pageable pageable = PageRequest.of(0, 2);
        doReturn(new PageImpl<>(customers, pageable, customers.size())).when(customerRepository).findAll((Pageable) Mockito.any());
    }

    @Test
    @DisplayName("filter by country")
    public void filterByCountry() throws Exception {

        String countryName = "Cameroon";
        mockMvc.perform(get(String.format("/customer/country/%s", countryName)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(containsString("MICHAEL MICHAEL"))).andExpect(jsonPath("$.content", hasSize(1)));
    }

    @Test
    @DisplayName("filter by state")
    public void filterByState() throws Exception {

        State state = State.VALID;
        mockMvc.perform(get(String.format("/customer/state/%s", state)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(containsString("MICHAEL MICHAEL"))).andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    @DisplayName("get all customers")
    public void getAllCustomers() throws Exception {

        mockMvc.perform(get("/customer").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(containsString("MICHAEL MICHAEL"))).andExpect(jsonPath("$.content", hasSize(2)));
    }
}
