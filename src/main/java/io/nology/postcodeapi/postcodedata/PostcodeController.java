package io.nology.postcodeapi.postcodedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postcodes")
public class PostcodeController {
	
	@Autowired
	PostcodeService postcodeService;
	
	@PostMapping
	public ResponseEntity<PostcodeEntity> createData(@Valid @RequestBody CreatePostcodeDataDTO data) {
		PostcodeEntity createdData = 
	}
	
	@GetMapping("/number/{suburb}")
	public ResponseEntity<PostcodeEntity> getPostcodeNumberFromSuburb(@PathVariable String suburb) {
		Optional<PostcodeEntity> maybeData
	}
	

}
