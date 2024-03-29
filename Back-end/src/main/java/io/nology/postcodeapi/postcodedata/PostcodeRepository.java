package io.nology.postcodeapi.postcodedata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<PostcodeEntity, Integer> {

	Optional<PostcodeEntity> findBySuburbName(String suburbName);
	Optional<PostcodeEntity> findByPostcodeNumber(Integer postcodeNumber);
	
}
