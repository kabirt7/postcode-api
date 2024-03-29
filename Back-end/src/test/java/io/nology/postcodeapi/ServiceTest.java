package io.nology.postcodeapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.nology.postcodeapi.exceptions.ServiceValidationException;
import io.nology.postcodeapi.postcodedata.CreatePostcodePairDTO;
import io.nology.postcodeapi.postcodedata.PostcodeEntity;
import io.nology.postcodeapi.postcodedata.PostcodeService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private PostcodeService postcodeService;

    @Test
    void testCreatePostcode() throws ServiceValidationException {
        CreatePostcodePairDTO testData = new CreatePostcodePairDTO(12345, "Test");

        PostcodeEntity createdPostcode = postcodeService.createData(testData);

        assertEquals(testData.getPostcodeNumber(), createdPostcode.getPostcodeNumber());
        assertEquals(testData.getSuburbName(), createdPostcode.getSuburbName());
        
    }

    @Test
    void testGetAllPostcodes() {
        List<PostcodeEntity> allPostcodes = postcodeService.getAllData();

        assertFalse(allPostcodes.isEmpty());
    }
    
    @Test
    void testGetPostcodeBySuburb() {
        Optional<Integer> postcode = postcodeService.getPostcodebySuburb("Test");

        assertTrue(postcode.isPresent());
        assertEquals(12345, postcode.get());
    }
    
    @Test
    void testGetSuburbByPostcode() {
        Optional<String> suburb = postcodeService.getSuburbByPostcode(12345);

        assertTrue(suburb.isPresent());
        assertEquals("Test", suburb.get());
    }

    @Test
    void testDeletePostcode() throws ServiceValidationException {

    	CreatePostcodePairDTO testData = new CreatePostcodePairDTO(12346, "Test2");
        postcodeService.createData(testData);
        
        boolean deleted = postcodeService.deleteDataById(12346);

        assertTrue(deleted);

    }
}
