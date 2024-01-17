package com.example.spring_testing_demo;

import com.example.spring_testing_demo.controllers.EmployeeController;
import com.example.spring_testing_demo.models.Employee;
import com.example.spring_testing_demo.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

    @MockBean
    private EmployeeService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getEmployees_ShouldReturnListOfEmployees() throws Exception{
        when(service.getAllEmployees()).thenReturn(getMockEmployees());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }


    private static Iterable<Employee> getMockEmployees(){
        Employee[] employeesArray = {
                new Employee("Test Employee 1", 1000),
                new Employee("Test Employee 2", 2000),
                new Employee("Test Employee 3", 3000),
        };

        return Arrays.stream(employeesArray).toList();
    }
}
