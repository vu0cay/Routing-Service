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
    // private String FeatureType;
    // private String Type;
    // private String UnitId; 
    private int Ordinal; 
    public org.neo4j.driver.types.Point geometry;
    
    private List<NearbyDTO> neigbors = new ArrayList<>();

    public AnchorDTO(AnchorDTO anchor) {
        this.EnityId = anchor.getEnityId();
        this.Id = anchor.getId();
        // this.FeatureType = anchor.getFeatureType();
        // this.Type = anchor.getType();
        // this.UnitId = anchor.getUnitId();
        this.Ordinal = anchor.getOrdinal();
        this.geometry = anchor.getGeometry();
        this.neigbors = anchor.getNeigbors();
    }
    

    public Long getEnityId() {
        return EnityId;
    }


    public String getId() {
        return Id;
    }


    public int getOrdinal() {
        return Ordinal;
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
        this.geometry = anchor.getGeometry();
        this.Ordinal = anchor.getOrdinal();

        anchor.getNeigbors().forEach(nei -> this.neigbors.add(new NearbyDTO(nei)));
    }

 

}
