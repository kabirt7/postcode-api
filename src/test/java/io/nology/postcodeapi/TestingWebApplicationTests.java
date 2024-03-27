package io.nology.postcodeapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.nology.postcodeapi.postcodedata.CreatePostcodePairDTO;
import io.nology.postcodeapi.postcodedata.PostcodeController;
import io.nology.postcodeapi.postcodedata.PostcodeEntity;
import io.nology.postcodeapi.postcodedata.PostcodeService;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTests {
    
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
        // mocking behavior of service
        when(service.createData(any(CreatePostcodePairDTO.class)))
            .thenAnswer(invocation -> {
                CreatePostcodePairDTO dto = invocation.getArgument(0);
                PostcodeEntity entity = new PostcodeEntity();
                entity.setPostcodeNumber(dto.getPostcodeNumber());
                entity.setSuburbName(dto.getSuburbName());
                return entity;
            });
        
        CreatePostcodePairDTO postcodeDto = new CreatePostcodePairDTO(6025, "Hillarys");
        
        // the previously defined service behaviour is mocked when from the Controller post method
        mockMvc.perform(post("/postcode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postcodeDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.postcodeNumber", equalTo(6025)))
                .andExpect(jsonPath("$.suburbName", equalTo("Hillarys")));
    }
}
