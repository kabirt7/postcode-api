package io.nology.postcodeapi.postcodedata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostcodeRepository extends JpaRepository<PostcodeEntity, Integer> {

	@Query("SELECT s.postcode FROM SuburbEntity s WHERE s.suburbName = :suburb")
	Optional<PostcodeEntity> findPostcodesBySuburbName(@Param("suburb") String suburb);

    
	Optional<PostcodeEntity> findByPostcodeNumber(Integer postcodeNumber);
}