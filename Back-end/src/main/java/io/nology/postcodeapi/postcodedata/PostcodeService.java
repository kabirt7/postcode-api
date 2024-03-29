package io.nology.postcodeapi.postcodedata;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.postcodeapi.exceptions.ServiceValidationException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostcodeService {

    private static final Logger logger = LoggerFactory.getLogger(PostcodeService.class);

    @Autowired
    private PostcodeRepository repo;

    public PostcodeEntity createData(CreatePostcodePairDTO data) throws ServiceValidationException {
        logger.info("Creating postcode data with: {}", data);

        PostcodeEntity newPair = new PostcodeEntity();
        newPair.setPostcodeNumber(data.getPostcodeNumber());
        newPair.setSuburbName(data.getSuburbName());

        PostcodeEntity savedPair = this.repo.save(newPair);
        logger.info("Postcode data created: {}", savedPair);

        return savedPair;
    }

    public List<PostcodeEntity> getAllData() {
        logger.info("Retrieving all postcode data");
        List<PostcodeEntity> allData = this.repo.findAll();
        logger.info("Retrieved {} postcode data entries", allData.size());
        return allData;
    }

    public Optional<Integer> getPostcodebySuburb(String suburb) {
        logger.info("Finding postcode for suburb: {}", suburb);
        Optional<PostcodeEntity> maybePostcodeEntity = repo.findBySuburbName(suburb);
        Optional<Integer> maybePostcode = maybePostcodeEntity.map(PostcodeEntity::getPostcodeNumber);
        logger.info("Found postcode {} for suburb: {}", maybePostcode.orElse(null), suburb);
        return maybePostcode;
    }

    public Optional<String> getSuburbByPostcode(Integer postcode) {
        logger.info("Finding suburb for postcode: {}", postcode);
        Optional<PostcodeEntity> maybePostcodeEntity = repo.findByPostcodeNumber(postcode);
        Optional<String> maybeSuburb = maybePostcodeEntity.map(PostcodeEntity::getSuburbName);
        logger.info("Found suburb {} for postcode: {}", maybeSuburb.orElse(null), postcode);
        return maybeSuburb;
    }

    public boolean deleteDataById(Integer id) {
        logger.info("Deleting postcode data with ID: {}", id);
        Optional<PostcodeEntity> maybeItem = this.repo.findByPostcodeNumber(id);

        if (maybeItem.isPresent()) {
            this.repo.delete(maybeItem.get());
            logger.info("Postcode data with ID {} deleted", id);
            return true;
        } else {
            logger.warn("Postcode data with ID {} not found for deletion", id);
            return false;
        }
    }
}
