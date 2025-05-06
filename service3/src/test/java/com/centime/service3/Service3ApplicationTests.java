package com.centime.service3;

import com.centime.commonservice.dtos.NameRequest;
import com.centime.service3.service.AppendService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Service3ApplicationTests {

	@Test
	void testGetBody() {
		NameRequest request = new NameRequest();
		request.setName("John");
		request.setSurname("Doe");

		String result = AppendService.getBody(request);
		assertEquals("John Doe", result);
	}

	@Test
	void testGetBodyNullValues() {

		NameRequest request = new NameRequest();
		request.setName(null);
		request.setSurname(null);

		String result = AppendService.getBody(request);
		assertEquals("null null", result);
	}

}
