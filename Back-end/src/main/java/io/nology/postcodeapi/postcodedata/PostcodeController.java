package io.nology.postcodeapi.postcodedata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.nology.postcodeapi.exceptions.NotFoundException;
import io.nology.postcodeapi.exceptions.ServiceValidationException;
import io.nology.postcodeapi.exceptions.ValidationErrors;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postcode")
public class PostcodeController {

    private static final Logger logger = LoggerFactory.getLogger(PostcodeController.class);

    @Autowired
    PostcodeService postcodeService;
    
    @Autowired
    PostcodeRepository repo;

    @PostMapping
    public ResponseEntity<PostcodeEntity> createPair(@Valid @RequestBody CreatePostcodePairDTO data) throws ServiceValidationException {
        
        Optional<PostcodeEntity> existingPostcodeOptional = repo.findById(data.getPostcodeNumber());
        
        if (existingPostcodeOptional.isPresent()) {
            logger.error("Postcode pair with postcode number {} already exists", data.getPostcodeNumber());
            ValidationErrors errors = new ValidationErrors();
            errors.addError("id", "Postcode pair with postcode " + data.getPostcodeNumber() + " already exists");
            
            throw new ServiceValidationException(errors);
        }
        
        logger.info("Creating postcode pair with data: {}", data);
        PostcodeEntity createdPair = this.postcodeService.createData(data);
        logger.info("Postcode pair created: {}", createdPair);
        return new ResponseEntity<>(createdPair, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostcodeEntity>> getAllPostcodeData() {
        logger.info("Getting all postcode data");
        List<PostcodeEntity> allPostcodeData = this.postcodeService.getAllData();
        logger.info("Retrieved {} postcode data", allPostcodeData.size());
        return new ResponseEntity<>(allPostcodeData, HttpStatus.OK);
    }

    @GetMapping("/number/{suburbName}")
    public ResponseEntity<Integer> getPostcodeFromSuburb(@PathVariable String suburbName) throws NotFoundException {
        logger.info("Getting postcode for suburb: {}", suburbName);
        Optional<Integer> maybePostcodeOptional = postcodeService.getPostcodebySuburb(suburbName);
        Integer postcodeItem = maybePostcodeOptional.orElseThrow(() -> {
        logger.error("Postcode not found for suburb: {}", suburbName);
        return new NotFoundException(suburbName);
        });
        logger.info("Found postcode {} for suburb: {}", postcodeItem, suburbName);
        return new ResponseEntity<>(postcodeItem, HttpStatus.OK);
    }

    @GetMapping("/name/{postcodeNumber}")
    public ResponseEntity<String> getSuburbFromPostcode(@PathVariable Integer postcodeNumber) throws NotFoundException {
        logger.info("Getting suburb for postcode: {}", postcodeNumber);
        Optional<String> maybeSuburbOptional = postcodeService.getSuburbByPostcode(postcodeNumber);
        String foundSuburb = maybeSuburbOptional.orElseThrow(() -> {
            logger.error("Suburb not found for postcode: {}", postcodeNumber);
            return new NotFoundException(postcodeNumber);
        });
        logger.info("Found suburb {} for postcode: {}", foundSuburb, postcodeNumber);
        return new ResponseEntity<>(foundSuburb, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostcodeEntity> deleteDataFromPostcode(@PathVariable Integer id) throws NotFoundException {
        logger.info("Deleting postcode data with ID: {}", id);
        boolean deleted = this.postcodeService.deleteDataById(id);
        if (!deleted) {
            logger.error("Failed to delete postcode data with ID: {}", id);
            throw new NotFoundException(id);
        }
        logger.info("Postcode data deleted successfully with ID: {}", id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}

