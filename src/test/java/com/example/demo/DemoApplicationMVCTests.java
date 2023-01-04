package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationMVCTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void testHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/api/employees")).andExpect(status().isOk());
	}

	@Test
	public void createEmployeeAPI() throws Exception {
		mvc.perform( MockMvcRequestBuilders
				.post("/v1/api/employee/create")
				.content(asJsonString(new Employee("Bill", "Venice")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(406));
	}


	public static String asJsonString(final Object obj) throws Exception {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
