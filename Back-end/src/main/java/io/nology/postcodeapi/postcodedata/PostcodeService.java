package io.nology.postcodeapi.postcodedata;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import io.nology.postcodeapi.exceptions.ServiceValidationException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostcodeService {

    private static final Logger logger = LoggerFactory.getLogger(PostcodeService.class);

    @Autowired
    private PostcodeRepository repo;
    
    @Autowired
    private SuburbRepository repoSuburb;
    
    public PostcodeEntity createData(CreatePostcodePairDTO data) throws ServiceValidationException {
        // Validate data if needed
        logger.info("Creating postcode data with DTO: {}", data);

        PostcodeEntity newPair = new PostcodeEntity();
        newPair.setPostcodeNumber(data.getPostcodeNumber());
        logger.info("New postcode entity created: {}", newPair);

// persist this first
        repo.save(newPair);
        
        Set<SuburbEntity> suburbEntities = new HashSet<>();
        for (SuburbEntity suburb : data.getSuburbs()) {
            logger.info("Processing suburb: {}", suburb.getSuburbName());

            SuburbEntity newSuburb = new SuburbEntity();


            String suburbName = suburb.getSuburbName();
            logger.info("Suburb name extracted: {}", suburbName);

            newSuburb.setPostcode(newPair);
            newSuburb.setSuburbName(suburbName);
            
            logger.info("Suburb ID before saving: {}", newSuburb.getId());

            // Save the new suburb to persist it and generate its ID
            newSuburb = repoSuburb.save(newSuburb);

            logger.info("Suburb ID after saving: {}", newSuburb.getId());

            suburbEntities.add(newSuburb);
            logger.info("Suburb entity added to set: {}", newSuburb);
        }
        
        newPair.setSuburbs(suburbEntities);
        logger.info("Set of suburb entities added to postcode entity: {}", suburbEntities);
        
   
        
        logger.info("Postcode entity saved: {}", newPair);

        return newPair;
    }


    public List<PostcodeEntity> getAllData() {
        logger.info("Retrieving all postcode data");
        List<PostcodeEntity> allData = this.repo.findAll();
        logger.info("Retrieved {} postcode data entries", allData.size());
        return allData;
    }

    public Optional<Integer> getPostcodebySuburb(String suburb) {
        logger.info("Finding postcode for suburb: {}", suburb);
        Optional<PostcodeEntity> maybePostcodeEntity = repo.findPostcodesBySuburbName(suburb);
        Optional<Integer> maybePostcode = maybePostcodeEntity.map(PostcodeEntity::getPostcodeNumber);
        logger.info("Found postcode {} for suburb: {}", maybePostcode.orElse(null), suburb);
        return maybePostcode;
    }

    public Optional<List<String>> getSuburbsByPostcode(Integer postcode) {
        logger.info("Finding suburbs for postcode: {}", postcode);
        Optional<PostcodeEntity> maybePostcodeEntity = repo.findByPostcodeNumber(postcode);
        
        Optional<List<String>> maybeSuburbs = maybePostcodeEntity.map(postcodeEntity -> 
            postcodeEntity.getSuburbs().stream()
                           .map(SuburbEntity::getSuburbName)
                           .collect(Collectors.toList())
        );
        
        logger.info("Found suburbs {} for postcode: {}", maybeSuburbs.orElse(null), postcode);
        return maybeSuburbs;
    }

    
    public boolean deleteDataById(Integer id) {
        logger.info("Deleting postcode data with ID: {}", id);
        Optional<PostcodeEntity> maybeItem = this.repo.findByPostcodeNumber(id);

        if (maybeItem.isPresent()) {
            PostcodeEntity postcodeEntity = maybeItem.get();
            try {
                // delete associated suburbs first
                for (SuburbEntity suburbEntity : postcodeEntity.getSuburbs()) {
                    repoSuburb.delete(suburbEntity);
                }
                
                // then delete the postcode entity
                this.repo.delete(postcodeEntity);
                logger.info("Postcode data with ID {} deleted", id);
                return true;
            } catch (DataIntegrityViolationException e) {
                logger.error("Failed to delete postcode data with ID {}: {}", id, e.getMessage());
                return false;
            }
        } else {
            logger.warn("Postcode data with ID {} not found for deletion", id);
            return false;
        }
    }
}
