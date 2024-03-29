package io.nology.postcodeapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.nology.postcodeapi.postcodedata.CreatePostcodePairDTO;
import io.nology.postcodeapi.postcodedata.PostcodeEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SmokeTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
    @Test
    void postShouldReturnCreatedResponse() throws Exception {

        CreatePostcodePairDTO testData = new CreatePostcodePairDTO(12345, "Test Suburb");
        
        ResponseEntity<PostcodeEntity> response = restTemplate.postForEntity("http://localhost:" + port + "/postcode", testData, PostcodeEntity.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }
    
    @Test 
    void deleteCreatedPost() throws Exception {
    	
        restTemplate.delete("http://localhost:" + port + "/postcode/12345");
        
        ResponseEntity<String> responseGetItem = restTemplate.getForEntity("http://localhost:" + port + "/postcode/name/12345", String.class);
        
        assertEquals("Could not find Postcode with number 12345", responseGetItem.getBody());
    }
    
    @Test
    void getAllReturnsOkResponse() throws Exception {

        ResponseEntity<PostcodeEntity[]> response = restTemplate.getForEntity("http://localhost:" + port + "/postcode", PostcodeEntity[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody().length > 0);
    }

}
