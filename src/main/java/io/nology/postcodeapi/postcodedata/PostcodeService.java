package io.nology.postcodeapi.postcodedata;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostcodeService {
	
	@Autowired
	private PostcodeRepository repo;
	
	@Autowired 
	private ModelMapper mapper;
	
	public Postcode createData(Create)
	
	
}
