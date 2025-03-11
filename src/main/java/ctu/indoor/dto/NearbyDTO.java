/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.dto;

import ctu.indoor.model.Anchor;
import ctu.indoor.model.Nearby;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@NoArgsConstructor
public class NearbyDTO {
    private Long Id;
    private  Anchor target;
    private double distance;

    public NearbyDTO(Nearby nearby) {
        this.Id = nearby.getId();
        this.target = nearby.getEndNode();
        this.distance = nearby.getDistance();
    }

    public Long getId() {
        return Id;
    }

    public Anchor getTarget() {
        return target;
    }

    public double getDistance() {
        return distance;
    }

    
}
