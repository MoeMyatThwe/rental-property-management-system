//PropertyTypeRepository.java
package com.nwe.rentalpropertybackend.repository;

import com.nwe.rentalpropertybackend.entity.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {

    Optional<PropertyType> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

}
