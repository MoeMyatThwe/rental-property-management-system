//PropertyTypeService.java
package com.nwe.rentalpropertybackend.service;

import com.nwe.rentalpropertybackend.entity.PropertyType;
import com.nwe.rentalpropertybackend.repository.PropertyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PropertyTypeService {
    private final PropertyTypeRepository propertyTypeRepository;

    public PropertyType createPropertyType(PropertyType propertyType){
        if (propertyTypeRepository.existsByNameIgnoreCase(propertyType.getName())) {
            throw new RuntimeException("Property type already exists: " + propertyType.getName());
        }
        return propertyTypeRepository.save(propertyType);
    }

    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeRepository.findAll();
    }

    public PropertyType getPropertyTypeById(Long id) {
        return propertyTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property type not found with id: " + id));
    }

    public PropertyType updatePropertyType(Long id, PropertyType updatedPropertyType) {
        PropertyType existingPropertyType = getPropertyTypeById(id);

        propertyTypeRepository.findByNameIgnoreCase(updatedPropertyType.getName())
                .ifPresent(propertyTypeWithSameName -> {
                    if (!propertyTypeWithSameName.getId().equals(id)) {
                        throw new RuntimeException("Property type already exists: " + updatedPropertyType.getName());
                    }
                });

        existingPropertyType.setName(updatedPropertyType.getName());
        existingPropertyType.setDescription(updatedPropertyType.getDescription());
        existingPropertyType.setActive(updatedPropertyType.getActive());

        return propertyTypeRepository.save(existingPropertyType);
    }

    public void deletePropertyType(Long id) {
         PropertyType existingPropertyType = getPropertyTypeById(id);
         propertyTypeRepository.delete(existingPropertyType);
    }
}
