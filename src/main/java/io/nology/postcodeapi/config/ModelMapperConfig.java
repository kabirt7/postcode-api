package io.nology.postcodeapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		
		
		// ensure no white space in suburb name 
		// makes suburb name lowercase
		
		// ensure no letters in long
	}
	
	private class StringTrimConverter implements Co
	
}
