package io.nology.postcodeapi.postcodedata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
//	@GetMapping("/number/{suburbName}")
//	public ResponseEntity<PostcodeEntity> getPostcodeNumberFromSuburb(@PathVariable String suburb) {
//		Optional<PostcodeEntity> maybeData
//	}
	
	// get /name/{postcodeNumber

}
