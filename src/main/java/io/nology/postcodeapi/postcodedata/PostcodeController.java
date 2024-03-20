package io.nology.postcodeapi.postcodedata;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postcode")
public class PostcodeController {
	
	@Autowired
	PostcodeService postcodeService;
	
	@PostMapping
	public ResponseEntity<PostcodeEntity> createPair(@Valid @RequestBody CreatePostcodePairDTO data) {
		PostcodeEntity createdPair = this.postcodeService.createData(data);
		return new ResponseEntity<>(createdPair, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PostcodeEntity>> getAllPostcodeData() {
		List<PostcodeEntity> allPostcodeData = this.postcodeService.getAllData();
		return new ResponseEntity<>(allPostcodeData, HttpStatus.OK);
	}
	
	@GetMapping("/number/{suburbName}")
	public ResponseEntity<Integer> getPostcodeFromSuburb(@PathVariable String suburbName) {
		
		Optional<Integer> maybePostcodeOptional = postcodeService.getPostcodebySuburb(suburbName);
		
		if (maybePostcodeOptional.isPresent()) {
			Integer postcodeItem = maybePostcodeOptional.get();
			return new ResponseEntity<>(postcodeItem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/name/{postcodeNumber}")
	public ResponseEntity<String> getSuburbFromPostcode(@PathVariable Integer postcodeNumber) {
		
		Optional<String> maybeSuburbOptional = postcodeService.getSuburbByPostcode(postcodeNumber);
		
		if (maybeSuburbOptional.isPresent()) {
			String suburbItem = maybeSuburbOptional.get();
			return new ResponseEntity<>(suburbItem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}

