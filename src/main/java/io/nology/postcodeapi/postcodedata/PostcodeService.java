package io.nology.postcodeapi.postcodedata;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostcodeService {
	
	@Autowired
	private PostcodeRepository repo;
	
//	@Autowired 
//	private ModelMapper mapper;
	
	// create pairing
	
	// get by postcodeNumber
	
	// get by suburbName
	
	
	public PostcodeEntity createData(CreatePostcodePairDTO data) {
		
		PostcodeEntity newPair = new PostcodeEntity();
		
		newPair.setPostcodeNumber(data.getPostcodeNumber());
		newPair.setSuburbName(data.getSuburbName());

		return this.repo.save(newPair);
	}
	
	public List<PostcodeEntity> getAllData() {
		return this.repo.findAll();
	}
	
	
}
