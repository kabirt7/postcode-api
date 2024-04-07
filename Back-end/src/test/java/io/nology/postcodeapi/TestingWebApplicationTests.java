package io.nology.postcodeapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

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
import io.nology.postcodeapi.postcodedata.SuburbEntity;

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
    void correctDataHandledByController() throws Exception {
        // mocking behavior of service
        when(service.createData(any(CreatePostcodePairDTO.class)))
            .thenAnswer(invocation -> {
                CreatePostcodePairDTO dto = invocation.getArgument(0);
                
                PostcodeEntity entity = new PostcodeEntity();
                entity.setPostcodeNumber(dto.getPostcodeNumber());
                entity.setSuburbs(dto.getSuburbs());
                return entity;
            });
        
        Set<SuburbEntity> suburbs = new HashSet<>();
        SuburbEntity testSuburb = new SuburbEntity();
        testSuburb.setSuburbName("Test");
        suburbs.add(testSuburb);
        
        CreatePostcodePairDTO postcodeDto = new CreatePostcodePairDTO(6025, suburbs);
        
        mockMvc.perform(post("/postcode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postcodeDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.postcodeNumber", equalTo(6025)))
                .andExpect(jsonPath("$.suburbs[0].suburbName", equalTo("Test")));
    }
}
