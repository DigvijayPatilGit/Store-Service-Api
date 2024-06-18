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

import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StoreAppRestApiApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	int counter;

	@Test
	void contextLoads() {
	}


	void createStore(){
		counter += 1;
		Store store = new Store(counter,"Raman"+counter,"Pune"+counter,234455.76+counter);
		ResponseEntity<ResponseStore> response = testRestTemplate
				.postForEntity("http://localhost:" + port +"/addItem",store,ResponseStore.class);
		Assertions.assertEquals(201,response.getStatusCode().value());
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

	@Test
	void shouldGetTheStoreList(){
		createStore();
		List<LinkedHashMap> list = testRestTemplate.getForObject("http://localhost:"+port+"/getItem",List.class);
		Assertions.assertNotNull(list);
		Assertions.assertEquals(1,list.get(0).get("storeId"));
		Assertions.assertTrue(list.size()>0);

	}

	@Test
	void shouldUpdate(){
		createStore();
		Store store = new Store(1,"Sonam","Mumbai",3355.76);
		testRestTemplate.put("http://localhost:"+port+"/update/1",store);
		List<LinkedHashMap> list = testRestTemplate.getForObject("http://localhost:"+port+"/getItem",List.class);
		Assertions.assertEquals("Sonam",list.get(0).get("ownerName"));
		Assertions.assertEquals(1,list.get(0).get("storeId"));
		Assertions.assertEquals(3355.76,list.get(0).get("revenue"));
		Assertions.assertEquals("Mumbai",list.get(0).get("location"));

	}


}
