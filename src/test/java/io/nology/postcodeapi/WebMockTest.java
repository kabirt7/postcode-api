package io.nology.postcodeapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.nology.postcodeapi.postcodedata.CreatePostcodePairDTO;
import io.nology.postcodeapi.postcodedata.PostcodeController;
import io.nology.postcodeapi.postcodedata.PostcodeEntity;
import io.nology.postcodeapi.postcodedata.PostcodeService;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PostcodeController.class)
public class WebMockTest {
	
	private String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostcodeService service;
	
	@Test
	void greetingShouldReturnMessageFromService() throws Exception {

// NEED TO MOCK THIS OTHERWISE REPO WILL INTERFERE		
		when(service.createData(any(CreatePostcodePairDTO.class)))
	    .thenAnswer(invocation -> {
	    	// get the argument passed in
	        CreatePostcodePairDTO dto = invocation.getArgument(0);
	        
	        // create a PostcodeEntity object with the same data
	        PostcodeEntity entity = new PostcodeEntity();
	        entity.setPostcodeNumber(dto.getPostcodeNumber());
	        entity.setSuburbName(dto.getSuburbName());
	        
	        // simulate saving the entity
	        return entity;
	    });
	    
	    CreatePostcodePairDTO postcodeDto = new CreatePostcodePairDTO(6025, "Hillarys");

	    mockMvc.perform(post("/postcode")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(postcodeDto)))
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.postcodeNumber", equalTo(6025)))
	            .andExpect(jsonPath("$.suburbName", equalTo("Hillarys")));
	    
	}
}