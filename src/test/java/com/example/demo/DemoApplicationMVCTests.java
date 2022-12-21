package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.EmployeeController;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



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

}
