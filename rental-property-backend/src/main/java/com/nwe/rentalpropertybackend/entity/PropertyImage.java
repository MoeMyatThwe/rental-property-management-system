// PropertyImage.java
package com.nwe.rentalpropertybackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "property_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @Builder.Default
    private Boolean primaryImage = false;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
}
