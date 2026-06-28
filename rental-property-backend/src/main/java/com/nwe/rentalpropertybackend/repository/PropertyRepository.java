//PropertyRepository.java
package com.nwe.rentalpropertybackend.repository;

import com.nwe.rentalpropertybackend.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long>{
}
