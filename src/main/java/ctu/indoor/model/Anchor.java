/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.model;

import java.util.List;

import org.neo4j.driver.types.Point;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@NodeEntity("ANCHOR")
@AllArgsConstructor
@NoArgsConstructor
public class Anchor {
    
    @Id
    @GeneratedValue
    private Long entity_Id;
    
    private String id;
    private String feature_type;
    private String type;
    private String unit_id; 
    private String level_id;
    public org.neo4j.driver.types.Point geometry;

    @JsonIgnore
    @Relationship(type= "NEARBY")   
    private List<Nearby> neigbors;

    public Long getEntity_Id() {
        return entity_Id;
    }

    public String getId() {
        return id;
    }

    public String getFeature_type() {
        return feature_type;
    }

    public String getType() {
        return type;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public String getLevel_id() {
        return level_id;
    }

    public org.neo4j.driver.types.Point getGeometry() {
        return geometry;
    }

    public List<Nearby> getNeigbors() {
        return neigbors;
    }

    public void setEntity_Id(Long entity_Id) {
        this.entity_Id = entity_Id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFeature_type(String feature_type) {
        this.feature_type = feature_type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public void setGeometry(Point geometry) {
        this.geometry = geometry;
    }

    public void setNeigbors(List<Nearby> neigbors) {
        this.neigbors = neigbors;
    }

   

    

}
