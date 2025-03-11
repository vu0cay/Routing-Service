/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.dto;

import java.util.ArrayList;
import java.util.List;

import ctu.indoor.model.Anchor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@NoArgsConstructor

public class AnchorDTO {

    private Long EnityId;
    private String Id;
    private String FeatureType;
    private String Type;
    private String UnitId; 
    private String LevelId; 
    public org.neo4j.driver.types.Point geometry;
    
    private List<NearbyDTO> neigbors = new ArrayList<>();

    public AnchorDTO(AnchorDTO anchor) {
        this.EnityId = anchor.getEnityId();
        this.Id = anchor.getId();
        this.FeatureType = anchor.getFeatureType();
        this.Type = anchor.getType();
        this.UnitId = anchor.getUnitId();
        this.LevelId = anchor.getLevelId();
        this.geometry = anchor.getGeometry();
        this.neigbors = anchor.getNeigbors();
    }

    public Long getEnityId() {
        return EnityId;
    }

    public String getId() {
        return Id;
    }

    public String getFeatureType() {
        return FeatureType;
    }

    public String getType() {
        return Type;
    }

    public String getUnitId() {
        return UnitId;
    }

    public String getLevelId() {
        return LevelId;
    }

    public org.neo4j.driver.types.Point getGeometry() {
        return geometry;
    }

    public List<NearbyDTO> getNeigbors() {
        return neigbors;
    }

    public AnchorDTO(Anchor anchor) {
        this.Id = anchor.getId();
        this.EnityId = anchor.getEntity_Id();
        this.FeatureType = anchor.getFeature_type();
        this.Type = anchor.getType();
        this.UnitId = anchor.getUnit_id();
        this.LevelId = anchor.getLevel_id();
        this.geometry = anchor.getGeometry();

        anchor.getNeigbors().forEach(nei -> this.neigbors.add(new NearbyDTO(nei)));
    }

}
