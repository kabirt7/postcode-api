package io.nology.postcodeapi.postcodedata;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<PostcodeEntity, Long> {

}
