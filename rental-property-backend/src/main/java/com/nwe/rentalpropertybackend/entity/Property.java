//Property.java
package com.nwe.rentalpropertybackend.entity;

import com.nwe.rentalpropertybackend.entity.enums.AreaUnit;
import com.nwe.rentalpropertybackend.entity.enums.AvailableStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyRent;

    @Column(precision = 10, scale = 2)
    private BigDecimal depositAmount;

    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    private Integer bedrooms;
    private Integer bathrooms;
    private Integer carParks;

    @Column(precision = 10, scale = 2)
    private BigDecimal area;

    @Enumerated(EnumType.STRING)
    private AreaUnit areaUnit;

    @Enumerated(EnumType.STRING)
    private AvailableStatus availableStatus;

    private LocalDate availableFrom;

    @Builder.Default
    private Boolean furnished = false;

    @Builder.Default
    private Boolean petFriendly = false;

    @Builder.Default
    private Boolean smokingAllowed = false;

    @ManyToOne
    @JoinColumn(name = "property_type_id", nullable = false)
    private PropertyType propertyType;

    @Builder.Default
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyImage> images = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist private void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (availableStatus == null) {
            availableStatus = AvailableStatus.AVAILABLE;
        }
    }

    @PreUpdate private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
