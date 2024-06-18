package com.example.Store_app_Rest_api;

import com.example.Store_app_Rest_api.dto.ResponseStore;
import com.example.Store_app_Rest_api.entity.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StoreAppRestApiApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
	}

	@Test
	void shoudCreateStoreTable(){
		Store store = new Store(1,"Raman","Pune",234455.76);
		ResponseEntity<ResponseStore> response = testRestTemplate
				.postForEntity("http://localhost:" + port +"/addItem",store,ResponseStore.class);

		Assertions.assertEquals(201,response.getStatusCode().value());
		ResponseStore body = response.getBody();
		Assertions.assertNotNull(body);
		Assertions.assertEquals("Data Store Successfully!",body.getMessage());
		Assertions.assertEquals(HttpStatus.CREATED,body.getHttpStatus());

	}


}
