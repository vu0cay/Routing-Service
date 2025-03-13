/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.dto;

import org.neo4j.driver.types.Point;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@NoArgsConstructor
public class AnchorPathDTO {
    private String Id;
    private String Ordinal;
    private Point Geometry;
    private double distance;

    public String getId() {
        return Id;
    }

    public String getOrdinal() {
        return Ordinal;
    }

    public Point getGeometry() {
        return Geometry;
    }

    public double getDistance() {
        return distance;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setOrdinal(String Ordinal) {
        this.Ordinal = Ordinal;
    }

    public void setGeometry(Point geometry) {
        Geometry = geometry;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }



}
