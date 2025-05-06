package com.centime.service1;

import com.centime.commonservice.dtos.NameRequest;
import com.centime.service1.service.ConcatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest
class Service1ApplicationTests {
	@Mock
	RestTemplate restTemplate;

	@Autowired
	ConcatService service;

	private NameRequest request;

	@BeforeEach
	void setUp() {
		request = new NameRequest();
		request.setName("John");
		request.setSurname("Doe");

		when(restTemplate.getForObject("http://localhost:8082/service2/hello", String.class)).thenReturn("Hello");
		when(restTemplate.postForObject("http://localhost:8083/service3/print", request, String.class))
				.thenReturn("John Doe");
	}

	@Test
	void testConcatNameRequest() {
		String result = service.concatNameRequest(request);
		assertEquals("Hello John Doe", result);
	}
}
