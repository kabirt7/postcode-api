package io.nology.postcodeapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.nology.postcodeapi.exceptions.ServiceValidationException;
import io.nology.postcodeapi.postcodedata.CreatePostcodePairDTO;
import io.nology.postcodeapi.postcodedata.PostcodeEntity;
import io.nology.postcodeapi.postcodedata.PostcodeService;
import io.nology.postcodeapi.postcodedata.SuburbEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private PostcodeService postcodeService;

    @Test
    void testCreatePostcode() throws ServiceValidationException {
    	Set<SuburbEntity> suburbs = new HashSet<>();
    	SuburbEntity testSuburb = new SuburbEntity();
    	testSuburb.setSuburbName("Test");
    	suburbs.add(testSuburb);
        CreatePostcodePairDTO testData = new CreatePostcodePairDTO(12345, suburbs);

        PostcodeEntity createdPostcode = postcodeService.createData(testData);

        assertEquals(testData.getPostcodeNumber(), createdPostcode.getPostcodeNumber());
//        assertEquals(testData.toString(), createdPostcode.toString());
// the above function didn't work as it was comparing the data location
// use the iterator and next methods to return the first suburb entities suburb Name
        assertEquals(testData.getSuburbs().iterator().next().getSuburbName(), 
                createdPostcode.getSuburbs().iterator().next().getSuburbName());
        
       postcodeService.deleteDataById(12345);
    }

    @Test
    void testGetAllPostcodes() {
        List<PostcodeEntity> allPostcodes = postcodeService.getAllData();

        assertFalse(allPostcodes.isEmpty());
    }
    
    @Test
    void testGetPostcodeBySuburb() throws ServiceValidationException {
    	
    	Set<SuburbEntity> suburbs = new HashSet<>();
    	SuburbEntity testSuburb = new SuburbEntity();
    	testSuburb.setSuburbName("Test");
    	suburbs.add(testSuburb);
    	
        CreatePostcodePairDTO testData = new CreatePostcodePairDTO(12345, suburbs);
        postcodeService.createData(testData);
    	
        Optional<Integer> postcode = postcodeService.getPostcodebySuburb("Test");

        assertTrue(postcode.isPresent());
        assertEquals(12345, postcode.get());
        
        postcodeService.deleteDataById(12345);
        
    }
    
    @Test
    void testGetSuburbByPostcode() throws ServiceValidationException {
    	Set<SuburbEntity> suburbs = new HashSet<>();
    	SuburbEntity testSuburb = new SuburbEntity();
    	testSuburb.setSuburbName("Test");
    	suburbs.add(testSuburb);
    	
        CreatePostcodePairDTO testData = new CreatePostcodePairDTO(12345, suburbs);
        postcodeService.createData(testData);
        
        Optional<List<String>> optionalSuburbsList = postcodeService.getSuburbsByPostcode(12345);
        
        assertTrue(optionalSuburbsList.isPresent());
        
       if (optionalSuburbsList.isPresent()) {
          List<String> suburbsList = optionalSuburbsList.get();
      
          assertEquals("Test", suburbsList.get(0));
       };
        
        postcodeService.deleteDataById(12345);
    }

    @Test
    void testDeletePostcode() throws ServiceValidationException {
    	
    	Set<SuburbEntity> suburbs = new HashSet<>();
    	SuburbEntity testSuburb = new SuburbEntity();
    	testSuburb.setSuburbName("Test2");
    	suburbs.add(testSuburb);

    	CreatePostcodePairDTO testData = new CreatePostcodePairDTO(12346, suburbs);
        postcodeService.createData(testData);
        
        boolean deleted = postcodeService.deleteDataById(12346);

        assertTrue(deleted);

    }
}
