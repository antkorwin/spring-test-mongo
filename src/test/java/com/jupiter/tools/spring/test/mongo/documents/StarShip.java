package com.jupiter.tools.spring.test.mongo.documents;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Document with the GeoJsonPoint field
 *
 * @author Korovin Anatoliy
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class StarShip {
    @Id
    private String id;
    private String name;

    private int armor;
    private int damage;

    private GeoJsonPoint location;
    private GeoJsonPolygon shape;
}
