package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;



@WebMvcTest(DemoApplication.class)
class DemoApplicationMVCTests {

	@Autowired
	private MockMvc mvc;


	@Test
	void testHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.get("/api/employee/1")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isNotFound());
	}

	@Test
	public void createEmployeeAPI() throws Exception {
		mvc.perform( MockMvcRequestBuilders
				.post("/api/saveEmployee")
				.content(asJsonString(new Employee("Bill", "Venice")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
