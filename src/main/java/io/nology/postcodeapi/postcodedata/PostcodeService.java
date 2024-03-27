package io.nology.postcodeapi.postcodedata;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.postcodeapi.exceptions.ServiceValidationException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostcodeService {
	
	@Autowired
	private PostcodeRepository repo;
	
	
	public PostcodeEntity createData(CreatePostcodePairDTO data) throws ServiceValidationException{
		
		PostcodeEntity newPair = new PostcodeEntity();
		
		newPair.setPostcodeNumber(data.getPostcodeNumber());
		newPair.setSuburbName(data.getSuburbName());

		return this.repo.save(newPair);
	}
	
	public List<PostcodeEntity> getAllData() {
		return this.repo.findAll();
	}
	
	// I don't think these need explicit error handling (Optionals kind of already handle this)
	public Optional<Integer> getPostcodebySuburb(String suburb) {
	  Optional<PostcodeEntity> maybePostcodeEntity = repo.findBySuburbName(suburb);
      return maybePostcodeEntity.map(PostcodeEntity::getPostcodeNumber);
	}
	
	public Optional<String> getSuburbByPostcode(Integer postcode) {
      Optional<PostcodeEntity> maybePostcodeEntity = repo.findByPostcodeNumber(postcode);
      return maybePostcodeEntity.map(PostcodeEntity::getSuburbName);
	}
	
	
}
