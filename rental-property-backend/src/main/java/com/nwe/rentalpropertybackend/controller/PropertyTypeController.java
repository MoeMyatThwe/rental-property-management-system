package com.nwe.rentalpropertybackend.controller;

import com.nwe.rentalpropertybackend.entity.PropertyType;
import com.nwe.rentalpropertybackend.service.PropertyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property-types")
@RequiredArgsConstructor

public class PropertyTypeController {

    private final PropertyTypeService propertyTypeService;

    @PostMapping
    public PropertyType createPropertyType(@RequestBody PropertyType propertyType){
        return propertyTypeService.createPropertyType(propertyType);
    }

    @GetMapping
    public List<PropertyType> getAllPropertyTypes(){
        return propertyTypeService.getAllPropertyTypes();
    }

    @GetMapping("/{id}")
    public PropertyType getPropertyTypeById(@PathVariable Long id){
        return propertyTypeService.getPropertyTypeById(id);
    }

    @PutMapping("/{id}")
    public PropertyType updatePropertyType(
            @PathVariable Long id,
            @RequestBody PropertyType propertyType) {
        return propertyTypeService.updatePropertyType(id,propertyType);
    }

    @DeleteMapping("/{id}")
    public void deletePropertyType(@PathVariable Long id){
        propertyTypeService.deletePropertyType(id);
    }
}
